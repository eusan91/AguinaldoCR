package com.santamaria.aguinaldocr

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by Santamaria on 29/08/2017.
 */
class MonthAdapter(private val monthList : ArrayList<Month>, private val context : Context, private val layout : Int) : BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return monthList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return monthList.size
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup) : View {

        val view: View
        val viewHolder : ViewHolder

        if (convertView == null){

            view = LayoutInflater.from(context).inflate(layout, viewGroup, false)
            viewHolder = ViewHolder(view, context)
            view.tag = viewHolder

        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.month.text = monthList[position].month
        viewHolder.amount.setText(monthList[position].amount.toString())
        if (monthList[position].amount > 0)
            viewHolder.amount.setText(monthList[position].amount.toString())
        else{
            viewHolder.amount.setText("")
        }

        viewHolder.amount.tag = position

        val focus = object : View.OnFocusChangeListener {

            override fun onFocusChange(p0: View?, p1: Boolean) {

                var amount = viewHolder.amount.text.toString()

                if (amount.isNotBlank())
                    monthList[viewHolder.amount.tag as Int].amount = amount.toFloat()
                else {
                    monthList[viewHolder.amount.tag as Int].amount = 0F
                }
            }
        }

        viewHolder.amount.setOnFocusChangeListener(focus)

        return view
    }

    private class ViewHolder(row: View, context: Context){
        var month : TextView = row.findViewById(R.id.monthId) as TextView
        var amount: EditText

        init {
            amount = row.findViewById(R.id.amountId) as EditText
        }
    }

}