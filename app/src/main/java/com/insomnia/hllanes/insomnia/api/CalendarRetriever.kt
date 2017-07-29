package com.insomnia.hllanes.insomnia.api

import com.insomnia.hllanes.insomnia.models.CalendarList
import retrofit2.Callback
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Interceptor



/**
 * Created by hllanes on 26/7/17.
 */



class CalendarRetriever {

    private var service:CalendarApi

    init {
        var interceptor = Interceptor { chain ->
            val request = chain?.request()?.newBuilder()?.addHeader("Authorization", "")?.build();
            chain?.proceed(request)
        }

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        var retrofit = Retrofit.Builder().
                baseUrl("http://ec2-52-89-198-196.us-west-2.compute.amazonaws.com/api/v1/").
                addConverterFactory(GsonConverterFactory.create()).
                client(httpClient.build()).
                build()

        service = retrofit.create(CalendarApi::class.java)
    }

    fun getCalendars(callback: Callback<CalendarList>){
            var call = service.getCalendars()
            call.enqueue(callback)
    }

}

