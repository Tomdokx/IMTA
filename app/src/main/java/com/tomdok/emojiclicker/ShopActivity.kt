package com.tomdok.emojiclicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.tomdok.emojiclicker.classes.Hero

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

        val heroes = listOf(
            Hero(0, 1, 88.6, R.drawable.avatar2),
            Hero(1, 10, 84.5, R.drawable.avatar2),
            Hero(2, 6, 23.3, R.drawable.avatar2),
            Hero(3, 82, 12.3, R.drawable.avatar2)
        )

        recyclerViewHeroes.adapter = RecyclerViewHeroesAdapter(applicationContext, heroes)
    }

    private fun upgrade() {
        TODO("Not yet implemented")
    }

    private fun goBackToGame() {
        finish()
    }

    private class RecyclerViewHeroesAdapter(private val context: Context, private val heroes: List<Hero>) : RecyclerView.Adapter<RecyclerViewHeroesAdapter.ViewHolder>() {

        class ViewHolder(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {

            val imageViewHero: ImageView = view.findViewById(R.id.imageViewHero)
            val textViewLevel: TextView = view.findViewById(R.id.textViewLevel)
            val textViewDps: TextView = view.findViewById(R.id.textViewDps)

            init {

                view.setOnClickListener {

                    parent.children.iterator().forEach { view ->
                        view.alpha = 0.5f
                    }
                    view.alpha = 1f
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val layoutInflater: LayoutInflater = context.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.recyclerview_row_hero, parent, false)
            return ViewHolder(view, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val hero = heroes[position]
            holder.imageViewHero.setImageResource(hero.picture)
            holder.textViewLevel.text = hero.level.toString()
            holder.textViewDps.text = hero.baseDPS.toString()
        }

        override fun getItemCount(): Int = heroes.size
    }
}