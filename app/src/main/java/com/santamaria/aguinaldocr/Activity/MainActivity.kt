package com.santamaria.aguinaldocr.Activity

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.kobakei.ratethisapp.RateThisApp
import com.santamaria.aguinaldocr.Adapter.MonthAdapter
import com.santamaria.aguinaldocr.Model.Month
import com.santamaria.aguinaldocr.R
import java.text.DecimalFormat
import java.util.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var monthList : ArrayList<Month> = ArrayList()
    private var df : DecimalFormat = DecimalFormat("#,###,###.##")
    private var year = Calendar.getInstance().get(Calendar.YEAR)

    //Ad variables
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLogo()

        rateThisApp()

        val listViewMonth : ListView = findViewById(R.id.listViewMonths) as ListView

        if (savedInstanceState != null){
            monthList = savedInstanceState.getParcelableArrayList("array")
        }

        if (monthList.size == 0){
            loadList()
        }


        var customAdapter = MonthAdapter(monthList, this)
        listViewMonth.adapter = customAdapter

        //Ad Code
        mAdView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)
    }

    private fun setLogo() {
        var ab = supportActionBar
        ab?.setLogo(R.mipmap.ic_launcher)
        ab?.setDisplayUseLogoEnabled(true);
        ab?.setDisplayShowHomeEnabled(true);
    }

    fun computeAguinaldo(v: View): Unit {

        var totalAmountSalaries : Double = 0.0
        var totalMonth : Int = 0

        for (item: Month in monthList){

            if (item.amount > 0) totalMonth++

            totalAmountSalaries += item.amount

        }

        alertDialogResult(totalAmountSalaries/12)
        //Toast.makeText(this, "Su aguinaldo será de:  ₡" +  df.format(totalAmountSalaries/12), Toast.LENGTH_LONG).show()

    }

    private fun loadList () {

        monthList.add(Month("Diciembre " + (year - 1)))
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

        val dialog : AlertDialog = AlertDialog.Builder(this).create()

        dialog.setTitle("Aguinaldo del "+ year)
        dialog.setMessage("Basado en los datos ingresados, su aguinaldo aproximado será de \n\n ₡ "+ df.format(aguinaldo))
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", { dialogInterface, i ->  })

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
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", DialogInterface.OnClickListener { dialogInterface, i -> })
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Si", DialogInterface.OnClickListener { dialogInterface, i ->
            this.finish()
        })
        alert.show()
    }

    override fun onBackPressed() {
        alertExitMessage()
    }

    private fun rateThisApp() {

        val config = RateThisApp.Config(3, 5)
        config.setTitle(R.string.rate_title)
        config.setMessage(R.string.rate_msg)
        config.setYesButtonText(R.string.rate_now)
        config.setNoButtonText(R.string.rate_no)
        config.setCancelButtonText(R.string.rate_later)
        RateThisApp.init(config)

        //Monitor launch times and interval from installation
        RateThisApp.onCreate(this)

        //If the condition is satisfied "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(this)

    }
}
