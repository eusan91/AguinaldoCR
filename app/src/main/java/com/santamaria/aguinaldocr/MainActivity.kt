package com.santamaria.aguinaldocr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import java.text.DecimalFormat
import java.util.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var monthList : ArrayList<Month> = ArrayList()
    var df : DecimalFormat = DecimalFormat("#,###,###.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewMonth : ListView = findViewById(R.id.listViewMonths) as ListView

        if (savedInstanceState != null){
            monthList = savedInstanceState.getParcelableArrayList("array")
        }

        if (monthList.size == 0){
            LoadList()
        }


        var customAdapter = MonthAdapter(monthList, this, R.layout.month_amount_item)
        listViewMonth.adapter = customAdapter

    }

    fun ComputeAguinaldo(v: View): Unit {

        var totalAmountSalaries : Double = 0.0
        var totalMonth : Int = 0

        for (item: Month in monthList){

            if (item.amount > 0) totalMonth++

            totalAmountSalaries += item.amount

        }

        Toast.makeText(this, "Su aguinaldo será de:  ₡" +  df.format(totalAmountSalaries/12), Toast.LENGTH_LONG).show()

    }

    fun LoadList () {

        //previous year
        val year = Calendar.getInstance().get(Calendar.YEAR)-1

        monthList.add(Month("Diciembre "+ year))
        monthList.add(Month("Enero"))
        monthList.add(Month("Febrero"))
        monthList.add(Month("Marzo"))
        monthList.add(Month("Abril"))
        monthList.add(Month("Mayo"))
        monthList.add(Month("Junio"))
        monthList.add(Month("Julio"))
        monthList.add(Month("Agosto"))
        monthList.add(Month("Setiembre"))
        monthList.add(Month("Octubre"))
        monthList.add(Month("Noviembre"))

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList("array",monthList)
    }
}
