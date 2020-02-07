package com.example.acculynx.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServicesProvider {

    /**
     * Instance of the [QuestionsInterface] service that is ready to use.
     */
    val questionService: QuestionsInterface


    init {
        val client =
            createOkHttpClient()

        questionService = provideStackExchangeRetrofit(
            client,
            provideGson()
        ).create(
            QuestionsInterface::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    private fun provideStackExchangeRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.stackexchange.com/2.2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private fun provideGson() = GsonBuilder()
        .create()
}