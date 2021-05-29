package com.tomdok.emojiclicker

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView

class ShopActivity : AppCompatActivity() {

    private val btnUpgrade by lazy{
        findViewById<Button>(R.id.btnUpgrade)
    }

    private val btnBackToGame by lazy{
        findViewById<Button>(R.id.btnBackToGame)
    }

    private val iVPlayer by lazy{
        findViewById<ImageView>(R.id.iVPlayer)
    }

    private val tVPlayer by lazy{
        findViewById<TextView>(R.id.tVPlayer)
    }

    val tVHero1 by lazy { findViewById<TextView>(R.id.tVHero1)}

    val tVHero2 by lazy { findViewById<TextView>(R.id.tVHero2)}

    val tVHero3 by lazy { findViewById<TextView>(R.id.tVHero3)}

    val tVHero4 by lazy { findViewById<TextView>(R.id.tVHero4)}

    private val tRPlayer by lazy{
        findViewById<TableRow>(R.id.tRPlayer)
    }
    private val tRHero1 by lazy{
        findViewById<TableRow>(R.id.tRHero1)
    }
    private val tRHero2 by lazy{
        findViewById<TableRow>(R.id.tRHero2)
    }
    private val tRHero3 by lazy{
        findViewById<TableRow>(R.id.tRHero3)
    }
    private val tRHero4 by lazy{
        findViewById<TableRow>(R.id.tRHero4)
    }

    private val tVCoins by lazy{
        findViewById<TextView>(R.id.tvTCoins)
    }

    private var l = listOf<TableRow>()
    private var active = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_shop)

        btnBackToGame.setOnClickListener { goBackToGame() }

        btnUpgrade.setOnClickListener { upgrade() }
        setAndTakeCareOfTableRows()

        setNames()
    }

    private fun setAndTakeCareOfTableRows() {
        l = listOf(tRPlayer,tRHero1,tRHero2,tRHero3,tRHero4)

        tRPlayer.setOnClickListener {
            active = 0
            highlight() }
        tRHero1.setOnClickListener {
            active = 1
            highlight() }
        tRHero2.setOnClickListener {
            active = 2
            highlight() }
        tRHero3.setOnClickListener {
            active = 3
            highlight() }
        tRHero4.setOnClickListener {
            active = 4
            highlight() }
    }

    private fun setNames(){
        tVPlayer.text = "Player"
        tVHero1.text = "Hero1"
        tVHero2.text = "Hero2"
        tVHero3.text = "Hero3"
        tVHero4.text = "Hero4"
    }

    private fun highlight() {
      //  l.get(ind).setBackgroundResource(R.drawable.ic_launcher_highlight)
        for (x in l){
            if(active == l.indexOf(x)){
                x.setBackgroundResource(R.drawable.ic_launcher_highlight)
            }
            else{
                x.setBackgroundResource(R.drawable.ic_launcher_bgtr)
            }
        }
    }

    private fun upgrade() {
        TODO("Not yet implemented")
    }

    private fun goBackToGame() {
        finish()
    }
}