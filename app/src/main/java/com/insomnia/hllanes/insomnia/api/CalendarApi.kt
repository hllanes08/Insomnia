package com.insomnia.hllanes.insomnia.api

import com.insomnia.hllanes.insomnia.models.CalendarList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import java.lang.reflect.Type

/**
 * Created by hllanes on 26/7/17.
 */
interface CalendarApi {

    //var token:String
    @GET("calendar")
    fun getCalendars() : Call<CalendarList>

}