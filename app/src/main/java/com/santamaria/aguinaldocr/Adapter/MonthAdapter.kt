package com.santamaria.aguinaldocr.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.santamaria.aguinaldocr.Model.Month
import com.santamaria.aguinaldocr.R
import java.util.*

/**
 * Created by Santamaria on 29/08/2017.
 */
class MonthAdapter(private val monthList : ArrayList<Month>, private val context : Context) : BaseAdapter() {

    var isUpdatingAmounts = false

    override fun getItem(p0: Int): Any {
        return monthList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return monthList.size
    }

    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == 0){
            0
        } else
            1
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup) : View {

        val view: View

        if (position == 0){
            val viewHolderPos0 : ViewHolderPosition0

            if (convertView == null){

                view = LayoutInflater.from(context).inflate(R.layout.first_month_item, viewGroup, false)
                viewHolderPos0 = ViewHolderPosition0(view)
                view.tag = viewHolderPos0

            } else {
                view = convertView
                viewHolderPos0 = convertView.tag as ViewHolderPosition0
            }

            viewHolderPos0.month.text = monthList[position].month
            viewHolderPos0.amount.setText(monthList[position].amount.toString())
            if (monthList[position].amount > 0)
                viewHolderPos0.amount.setText(monthList[position].amount.toString())
            else{
                viewHolderPos0.amount.setText("")
            }

            viewHolderPos0.amount.tag = position


            if (viewHolderPos0.check.onFocusChangeListener == null ){

                val focus = View.OnFocusChangeListener { _, _ ->

                    var amount = viewHolderPos0.amount.text.toString()

                    if (amount.isNotBlank())
                        monthList[viewHolderPos0.amount.tag as Int].amount = amount.toDouble()
                    else {
                        monthList[viewHolderPos0.amount.tag as Int].amount = 0.0
                    }
                }

                viewHolderPos0.amount.onFocusChangeListener = focus

            }

            viewHolderPos0.check.setOnClickListener({ view ->

                val check = view as CheckBox

                isUpdatingAmounts = true

                if (check.isChecked && monthList[0].amount > 0 ){

                    val decAmount = monthList[0].amount

                    for (monthItem in monthList){

                        monthItem.amount = decAmount

                    }

                    notifyDataSetChanged()
                    hideSoftKeyboard(check)

                }

            })

        } else {

            val viewHolder : ViewHolder

            if (convertView == null){

                view = LayoutInflater.from(context).inflate(R.layout.month_amount_item, viewGroup, false)
                viewHolder = ViewHolder(view)
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

            if (viewHolder.amount.onFocusChangeListener == null){

                val focus = View.OnFocusChangeListener { _, _ ->

                    if (!isUpdatingAmounts) {
                        var amount = viewHolder.amount.text.toString()

                        if (amount.isNotBlank() && amount.length > 1)
                            monthList[viewHolder.amount.tag as Int].amount = amount.toDouble()
                        else {
                            monthList[viewHolder.amount.tag as Int].amount = 0.0
                        }
                    } else {
                        isUpdatingAmounts = false
                    }
                }

                viewHolder.amount.onFocusChangeListener = focus

            }
        }

        return view
    }

    private fun hideSoftKeyboard(view : View ){

        // Check if no view has focus:
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    private class ViewHolder(row: View){
        var month : TextView = row.findViewById(R.id.monthId) as TextView
        var amount: EditText

        init {
            amount = row.findViewById(R.id.amountId) as EditText
        }
    }

    private class ViewHolderPosition0(row: View){
        var month : TextView = row.findViewById(R.id.monthId) as TextView
        var check : CheckBox = row.findViewById(R.id.checkBoxSameAmount) as CheckBox
        var amount: EditText

        init {
            amount = row.findViewById(R.id.amountId) as EditText
        }
    }

}