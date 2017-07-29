package com.insomnia.hllanes.insomnia.models

/**
 * Created by hllanes on 26/7/17.
 */
data class Calendar(val id:Int,
                    var name:String,
                    var start_date:String,
                    var end_date: String,
                    var n_days: Int  ) {
}