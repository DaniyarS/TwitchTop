package dev.dslam.twitchtop.repository

import androidx.lifecycle.MutableLiveData
import dev.dslam.twitchtop.db.TopGameDao
import dev.dslam.twitchtop.models.TopGame
import dev.dslam.twitchtop.models.TopGameResponse
import dev.dslam.twitchtop.remote.TwitchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TopGameRepository @Inject constructor(
    private val twitchService: TwitchService,
    private val topGameDao: TopGameDao
) {

    fun apiCall(topGamesList: MutableLiveData<List<TopGame>>) {
        val call: Call<TopGameResponse> = twitchService.getTopGamesList()
        call.enqueue(object : Callback<TopGameResponse> {
            override fun onResponse(
                call: Call<TopGameResponse>,
                response: Response<TopGameResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.items?.let { insertLocalData(it) }
                    topGamesList.postValue(getLocalData())
                } else {
                    if (getLocalData() != null) {
                        topGamesList.postValue(getLocalData())
                    } else {
                        topGamesList.postValue(null)
                    }
                }
            }

            override fun onFailure(call: Call<TopGameResponse>, t: Throwable) {
                if (getLocalData() != null) {
                    topGamesList.postValue(getLocalData())
                } else {
                    topGamesList.postValue(null)
                }
            }
        })
    }

    private fun getLocalData(): List<TopGame>? {
        return topGameDao.getTopGameList()
    }

    private fun insertLocalData(topGameList: List<TopGame>) {
        topGameDao.insertTopGameList(topGameList)
    }
}
