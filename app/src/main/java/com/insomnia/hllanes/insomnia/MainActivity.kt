package com.insomnia.hllanes.insomnia

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.insomnia.hllanes.insomnia.api.CalendarRetriever
import com.insomnia.hllanes.insomnia.models.Calendar
import com.insomnia.hllanes.insomnia.models.CalendarList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var calendars : List<Calendar>? = null
    var mainAdapter : MainAdapter? = null
    lateinit var recycleView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        recycleView = findViewById(R.id.recicleView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this)

        var retriever = CalendarRetriever()

        var callback  = object : Callback<CalendarList> {
            override fun onFailure(call: Call<CalendarList>?, t: Throwable?) {
               Log.e("MainActivity", "Problems calling API", t)
            }

            override fun onResponse(call: Call<CalendarList>?, response: Response<CalendarList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.calendars = response?.body()?.calendars
                    mainAdapter = MainAdapter(this@MainActivity.calendars!!, this@MainActivity)
                    recycleView.adapter = mainAdapter
                }
            }

        }
        retriever.getCalendars(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {

    }
}
