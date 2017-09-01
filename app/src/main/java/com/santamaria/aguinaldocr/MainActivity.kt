package com.santamaria.aguinaldocr

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ListView
import android.widget.Toast
import java.text.DecimalFormat
import java.util.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var monthList : ArrayList<Month> = ArrayList()
    private var df : DecimalFormat = DecimalFormat("#,###,###.##")
    private var year = Calendar.getInstance().get(Calendar.YEAR)

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

        alertDialogResult(totalAmountSalaries/12)
        //Toast.makeText(this, "Su aguinaldo será de:  ₡" +  df.format(totalAmountSalaries/12), Toast.LENGTH_LONG).show()

    }

    private fun LoadList () {

        monthList.add(Month("Diciembre "+ (year-1)))
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

    private fun alertDialogResult(aguinaldo : Double) {

        val dialog : AlertDialog = AlertDialog.Builder(this@MainActivity).create()

        dialog.setTitle("Aguinaldo del "+ year)
        dialog.setMessage("Basado en los datos ingresados su Aguinaldo aproximado será de \n\n ₡ "+ df.format(aguinaldo))
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", DialogInterface.OnClickListener { dialogInterface, i ->  })

        dialog.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.exit_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.exit_menu_item -> alertExitMessage()

        }
        return super.onOptionsItemSelected(item)
    }


    private fun alertExitMessage() : Unit {

        val alert = AlertDialog.Builder(this).create()

        alert.setTitle("¿Salir?")
        alert.setMessage("¿Seguro que desea cerrar la aplicación?")
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", DialogInterface.OnClickListener { dialogInterface, i ->
            this.finish()
        })
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", DialogInterface.OnClickListener { dialogInterface, i -> })

        alert.show()


    }
}
