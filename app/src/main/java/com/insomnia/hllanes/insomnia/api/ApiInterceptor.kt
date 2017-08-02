package com.insomnia.hllanes.insomnia.api

/**
 * Created by hllanes on 29/7/17.
 */

import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor(val apiKey: String, val cacheDuration: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter("Authorization", apiKey)
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .addHeader("Cache-Control", "public, max-age=$cacheDuration")
                .build()

        return chain.proceed(newRequest)
    }
}