package com.tomdok.emojiclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_record)
    }
}