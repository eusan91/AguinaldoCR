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

    private val ARRAY_LIST_PARAMETER = "array"

    //Ad variables
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLogo()

        rateThisApp()

        val listViewMonth : ListView = findViewById(R.id.listViewMonths) as ListView

        if (savedInstanceState != null){
            monthList = savedInstanceState.getParcelableArrayList(ARRAY_LIST_PARAMETER)
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

    }

    private fun loadList () {

        monthList.add(Month(getString(R.string.diciembre) + " " + (year - 1)))
        monthList.add(Month(getString(R.string.enero)))
        monthList.add(Month(getString(R.string.febrero)))
        monthList.add(Month(getString(R.string.marzo)))
        monthList.add(Month(getString(R.string.abril)))
        monthList.add(Month(getString(R.string.mayo)))
        monthList.add(Month(getString(R.string.junio)))
        monthList.add(Month(getString(R.string.julio)))
        monthList.add(Month(getString(R.string.agosto)))
        monthList.add(Month(getString(R.string.setiembre)))
        monthList.add(Month(getString(R.string.octubre)))
        monthList.add(Month(getString(R.string.noviembre)))

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(ARRAY_LIST_PARAMETER, monthList)
    }

    private fun alertDialogResult(aguinaldo : Double) {

        val dialog : AlertDialog = AlertDialog.Builder(this).create()

        dialog.setTitle(getString(R.string.dialog_result_title)+ year)
        dialog.setMessage(getString(R.string.dialog_result_message) + "\n\n"+ getString(R.string.currency_symbol) + df.format(aguinaldo))
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.dialog_result_button_ok), { _, _ ->  })

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

    private fun alertExitMessage() {

        val alert = AlertDialog.Builder(this).create()

        alert.setTitle(getString(R.string.dialog_exit_title))
        alert.setMessage(getString(R.string.dialog_exit_message))
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.dialog_exit_no), { _, _ -> })
        alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dialog_exit_si), { _, _ -> this.finish() })
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
