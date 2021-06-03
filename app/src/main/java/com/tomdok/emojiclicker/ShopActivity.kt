package com.tomdok.emojiclicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player

class ShopActivity : AppCompatActivity() {

    private val btnUpgrade by lazy {
        findViewById<Button>(R.id.btnUpgrade)
    }

    private val btnBackToGame by lazy {
        findViewById<Button>(R.id.btnBackToGame)
    }

    private val tVCoins by lazy {
        findViewById<TextView>(R.id.tvTCoins)
    }

    private val recyclerViewHeroes by lazy {
        findViewById<RecyclerView>(R.id.recyclerViewHeroes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_shop)

        btnBackToGame.setOnClickListener { goBackToGame() }
        btnUpgrade.setOnClickListener { upgrade() }

        val player = Player("Player1", 1, 100, 90.6)
        val heroes = listOf(
            Hero(0, "Hero1",1, 88.6, R.drawable.avatar2),
            Hero(1, "Hero2",10, 84.5, R.drawable.avatar2),
            Hero(2, "Hero3",6, 23.3, R.drawable.avatar2),
            Hero(3, "Hero4",82, 12.3, R.drawable.avatar2),
        )

        tVCoins.text = player.tCoins.toString()

        recyclerViewHeroes.adapter = ShopRecyclerViewAdapter(applicationContext, heroes, player,
            onClick = { holder, selectedPosition ->

                for (i in 1 until recyclerViewHeroes.adapter?.itemCount!!) {

                    val holder = recyclerViewHeroes.findViewHolderForAdapterPosition(i) as ShopRecyclerViewAdapter.ViewHolderHero?
                    holder?.selected = false
                }

                when (selectedPosition) {

                    0 -> {

                        val holder = holder as ShopRecyclerViewAdapter.ViewHolderPlayer
                        holder.selected = true
                    }
                    else -> {

                        val holder = holder as ShopRecyclerViewAdapter.ViewHolderHero
                        holder.selected = true
                        val playerHolder: ShopRecyclerViewAdapter.ViewHolderPlayer? = recyclerViewHeroes.findViewHolderForAdapterPosition(0) as ShopRecyclerViewAdapter.ViewHolderPlayer?
                        playerHolder?.selected = false
                    }
                }
        })
    }

    private fun upgrade() {

        TODO("Not yet implemented")
    }

    private fun goBackToGame() {

        finish()
    }
}
