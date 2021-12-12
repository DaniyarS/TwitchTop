package dev.dslam.twitchtop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dslam.twitchtop.models.TopGame
import dev.dslam.twitchtop.repository.TopGameRepository
import javax.inject.Inject

@HiltViewModel
class TopGameViewModel @Inject constructor(private val repository: TopGameRepository) : ViewModel() {

    private var topGameList: MutableLiveData<List<TopGame>> = MutableLiveData()

    fun getTopGamesObserver() : MutableLiveData<List<TopGame>> {
        return topGameList
    }

    fun loadTopGamesList() {
        repository.apiCall(topGameList)
    }
}
