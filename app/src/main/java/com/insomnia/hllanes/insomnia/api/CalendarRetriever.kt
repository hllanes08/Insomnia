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

        val httpClient = OkHttpClient.Builder()
        var client = httpClient
                .addInterceptor { chain ->
                    val request = chain.request()
                    val newrequest = request.newBuilder()
                            .addHeader("Authorization", "qzz3oPxLJXLTPcmGmQGD")
                            .addHeader("Accept","application/json")
                            .build()
                    chain.proceed(newrequest)
                }
                .build()
        var retrofit = Retrofit.Builder().
                //baseUrl("http://10.0.2.2:3000/api/v1/").
                baseUrl("http://ec2-52-89-198-196.us-west-2.compute.amazonaws.com/api/v1/").
                addConverterFactory(GsonConverterFactory.create()).
                client(client).
                build()

        service = retrofit.create(CalendarApi::class.java)
    }

    fun getCalendars(callback: Callback<CalendarList>){
            var call = service.getCalendars()
            call.enqueue(callback)
    }

}

