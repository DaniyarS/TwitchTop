package dev.dslam.twitchtop.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dslam.twitchtop.db.AppDatabase
import dev.dslam.twitchtop.db.TopGameDao
import dev.dslam.twitchtop.remote.TwitchService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val baseURL = "https://api.twitch.tv/"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providedTwitchService(retrofit: Retrofit) : TwitchService {
        return retrofit.create(TwitchService::class.java)
    }

    @Singleton
    @Provides
    fun getAppDB(context: Application): AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): TopGameDao {
        return appDB.getTopGameDao()
    }
}