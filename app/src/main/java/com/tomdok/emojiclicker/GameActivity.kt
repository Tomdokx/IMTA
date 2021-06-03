package com.tomdok.emojiclicker

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.tomdok.emojiclicker.classes.Biome
import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Player
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private val btnToShop by lazy {
        findViewById<Button>(R.id.btnShop)
    }

    private val btnAbility by lazy {
        findViewById<Button>(R.id.btnAbility)
    }

    private val btnToMenu by lazy {
        findViewById<Button>(R.id.btnMenu)
    }

    private val iBtnEmote by lazy {
        findViewById<ImageButton>(R.id.ibtnMonster)
    }

    private val tVCoins by lazy{
        findViewById<TextView>(R.id.tvTCoinsGame)
    }

    private var tCoins = 0
    val player = Player("Name",1,90, 84.4)
    val emote = Emote(0,"NameEmote",500.0,R.drawable.avatar2)

    private val rnd = Random(8654231597)

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

        iBtnEmote.setOnClickListener { doDpsClick() }
    }

    private fun doDpsClick() {

    player.clickAndDoDps(emote)

    tCoins += rnd.nextInt(1,5)

    tVCoins.text = tCoins.toString()
    }

    private fun goToShopActivity() {

        val intent = Intent(this,ShopActivity::class.java)

        startActivity(intent)
    }

    private fun goToMenuActivity() {

        // uložení do databáze? ? ?

        finish()
    }

    private fun doAbility() {

        TODO("Not yet implemented")
    }
}