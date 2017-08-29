package com.santamaria.aguinaldocr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    var monthList = LinkedList<Month>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewMonth : ListView = findViewById(R.id.listViewMonths) as ListView

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

        var customAdapter = MonthAdapter(monthList, this, R.layout.month_amount_item)
        listViewMonth.adapter = customAdapter

    }

    fun ComputeAguinaldo(v: View): Unit {

        Toast.makeText(this, "Su aguinaldo será de:  ₡" + 100, Toast.LENGTH_LONG).show()

    }
}
