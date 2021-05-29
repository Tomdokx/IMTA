package com.tomdok.emojiclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameActivity : AppCompatActivity() {

    private val btnToShop by lazy{
        findViewById<Button>(R.id.btnShop)
    }

    private val btnAbility by lazy{
        findViewById<Button>(R.id.btnAbility)
    }

    private val btnToMenu by lazy{
        findViewById<Button>(R.id.btnMenu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {

        }

        setContentView(R.layout.activity_game)

        btnToMenu.setOnClickListener { goToMenuActivity() }
        btnToShop.setOnClickListener { goToShopActivity() }
        btnAbility.setOnClickListener { doAbility() }

    }

    private fun goToShopActivity() {
        val intent = Intent(this,ShopActivity::class.java)
        startActivity(intent)
    }

    private fun goToMenuActivity() {
        finish()
    }

    private fun doAbility() {
        TODO("Not yet implemented")
    }
}