package com.insomnia.hllanes.insomnia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.insomnia.hllanes.insomnia.models.Calendar

/**
 * Created by hllanes on 29/7/17.
 */
class MainAdapter(var calendars: List<Calendar>,
                  var clickListener: View.OnClickListener) : RecyclerView.Adapter<MainAdapter.CalendarViewHolder>() {
    override fun onBindViewHolder(holder: CalendarViewHolder?, position: Int) {
        var calendar = calendars[position]
        holder?.calendarName?.text = calendar.name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.calendar_item, parent, false))
    }

    override fun getItemCount(): Int {
        return calendars.size
    }

    inner class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var calendarName: TextView

        init{
            if(clickListener != null){
                itemView.setOnClickListener { clickListener }
            }
            calendarName = itemView.findViewById(R.id.calendarName) as TextView
        }
    }

}