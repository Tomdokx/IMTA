package com.tomdok.emojiclicker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val btnToGame by lazy {
        findViewById<Button>(R.id.btnGame)
    }

    private val btnToRecords by lazy {
        findViewById<Button>(R.id.btnRecords)
    }

    private val btnToInfo by lazy {
        findViewById<Button>(R.id.btnAbout)
    }

    private val btnToSettings by lazy {
        findViewById<Button>(R.id.btnSettings)
    }

    private val btnQuit by lazy {
        findViewById<Button>(R.id.btnQuit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_main)

        btnToGame.setOnClickListener { goToActivity(1) }
        btnToRecords.setOnClickListener { goToActivity(2) }
        btnToInfo.setOnClickListener { goToActivity(3) }
        btnToSettings.setOnClickListener { goToActivity(4) }
        btnQuit.setOnClickListener { quitTheGame() }
    }

    private fun quitTheGame() {

        finish()
    }

    override fun onDestroy() {

        //TODO save the record..

        super.onDestroy()
    }

    private fun goToActivity(act: Int) {

        val intentToActivity = Intent()

        when (act) {
            1 -> intentToActivity.setClass(this,GameActivity::class.java)
            2 -> intentToActivity.setClass(this,RecordActivity::class.java)
            3 -> intentToActivity.setClass(this,InfoActivity::class.java)
            4 -> intentToActivity.setClass(this,SettingsActivity::class.java)

            else -> {}
        }

        startActivity(intentToActivity)
    }
}