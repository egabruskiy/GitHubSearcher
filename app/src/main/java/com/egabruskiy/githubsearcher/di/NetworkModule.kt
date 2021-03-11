package com.egabruskiy.githubsearcher.di

import com.egabruskiy.githubsearcher.network.GitRepoImpl
import com.egabruskiy.githubsearcher.BuildConfig
import com.egabruskiy.githubsearcher.network.GitRepoRemote
import com.egabruskiy.githubsearcher.network.SearchService
import com.egabruskiy.githubsearcher.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


val networkModule = module {

    single { provideLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get()) }

    single { provideSearchService(get()) }

    single<GitRepoRemote> { provideGitRepoRemoteImpl(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {

    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {

            HttpLoggingInterceptor.Level.NONE

        }
    }
}

private fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideSearchService(retrofit: Retrofit): SearchService {
    return retrofit.create(SearchService::class.java)
}

private fun provideGitRepoRemoteImpl(
    service: SearchService
): GitRepoImpl {
    return GitRepoImpl(service)
}
