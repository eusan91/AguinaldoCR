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
import java.util.*

/**
 * Created by Santamaria on 29/08/2017.
 */
class MonthAdapter(val monthList : ArrayList<Month>, val context : Context, val layout : Int) : BaseAdapter() {


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
        viewHolder.amount.setTag(position)

        viewHolder.amount.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

                monthList[viewHolder.amount.tag as Int].amount = viewHolder.amount.text.toString().toDouble()
                //Toast.makeText(context, viewHolder.amount.text, Toast.LENGTH_SHORT).show()
                //Toast.makeText(context, viewHolder.amount.tag.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

        })

        return view
    }

    private class ViewHolder(row: View, context: Context){
        var month : TextView
        var amount: EditText

        init {
            month = row.findViewById(R.id.monthId) as TextView
            amount = row.findViewById(R.id.amountId) as EditText

            amount.setOnClickListener(View.OnClickListener {

            })


        }
    }

}