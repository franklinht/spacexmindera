package com.frank.spacexmindera.data.api

import com.frank.spacexmindera.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RFBuilder {
    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
//            .client(client) // it matters if u want to log the retrofit handling
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val api: Api = getRetrofit().create(Api::class.java)
}