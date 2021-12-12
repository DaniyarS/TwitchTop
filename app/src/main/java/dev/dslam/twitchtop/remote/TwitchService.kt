package dev.dslam.twitchtop.remote

import dev.dslam.twitchtop.models.TopGameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchService {

    @Headers(
        "Authorization: Bearer 7hq7mo22fniilrcud3dwml32mxchj9",
        "Client-Id: ahuoi1tl0qmqbyi8jo8nitbmuaad7w"
    )
    @GET("helix/games/top")
    fun getTopGamesList(): Call<TopGameResponse>
}
