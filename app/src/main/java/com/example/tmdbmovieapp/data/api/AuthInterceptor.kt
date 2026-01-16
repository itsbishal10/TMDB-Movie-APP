package com.example.tmdbmovieapp.data.api

import android.util.Log
import com.example.tmdbmovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("AUTH", "AuthInterceptor called")

        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}"
            )
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}
