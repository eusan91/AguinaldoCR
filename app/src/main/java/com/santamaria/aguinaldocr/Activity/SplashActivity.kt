package com.santamaria.aguinaldocr.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent = Intent(this, MainActivity::class.java)
        Thread.sleep(1500)
        startActivity(intent)

        finish()
    }
}
