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

        recyclerViewHeroes.adapter = RecyclerViewHeroesAdapter(applicationContext, heroes, player,
            onClick = { holder, selectedPosition ->

                for (i in 1 until recyclerViewHeroes.adapter?.itemCount!!) {

                    val holder = recyclerViewHeroes.findViewHolderForAdapterPosition(i) as RecyclerViewHeroesAdapter.ViewHolderHero?
                    holder?.selected = false
                }

                when (selectedPosition) {

                    0 -> {

                        val holder = holder as RecyclerViewHeroesAdapter.ViewHolderPlayer
                        holder.selected = true
                    }
                    else -> {

                        val holder = holder as RecyclerViewHeroesAdapter.ViewHolderHero
                        holder.selected = true
                        val playerHolder: RecyclerViewHeroesAdapter.ViewHolderPlayer? = recyclerViewHeroes.findViewHolderForAdapterPosition(0) as RecyclerViewHeroesAdapter.ViewHolderPlayer?
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

    class RecyclerViewHeroesAdapter(
        private val context: Context,
        private val heroes: List<Hero>,
        private val player: Player,
        private val onClick: (holder: RecyclerView.ViewHolder, selectedPosition: Int) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var selectedPosition: Int = 0

        class ViewHolderPlayer(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {

            val imageViewPlayer: ImageView = view.findViewById(R.id.imageViewPlayer)
            val textViewName: TextView = view.findViewById(R.id.recyclerview_row_player_textViewName)
            val textViewLevel: TextView = view.findViewById(R.id.recyclerview_row_player_textViewLevel)

            var selected: Boolean = false
                set(value) {

                    field = value
                    if (value) {

                        itemView.background.alpha = 255
                    } else {

                        itemView.background.alpha = 0
                    }
                }

            var onClick: (() -> Unit)? = null

            init {

                itemView.setBackgroundResource(R.drawable.ic_launcher_highlight)
                itemView.background.alpha = 0

                view.setOnClickListener {
                    onClick?.invoke()
                }
            }
        }

        inner class ViewHolderHero(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {

            val imageViewHero: ImageView = view.findViewById(R.id.imageViewHero)
            val textViewName: TextView = view.findViewById(R.id.recyclerview_row_hero_textViewName)
            val textViewLevel: TextView = view.findViewById(R.id.recyclerview_row_player_textViewLevel)

            var selected: Boolean = false
                set(value) {

                    field = value
                    if (value) {

                        itemView.background.alpha = 255
                    } else {

                        itemView.background.alpha = 0
                    }
                }

            var onClick: (() -> Unit)? = null

            init {

                itemView.setBackgroundResource(R.drawable.ic_launcher_highlight)
                itemView.background.alpha = 0

                view.setOnClickListener {
                    onClick?.invoke()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val layoutInflater: LayoutInflater = context.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            return when (viewType) {

                0 -> {

                    val view = layoutInflater.inflate(R.layout.recyclerview_row_player, parent, false)
                    ViewHolderPlayer(view, parent)
                }
                else -> {

                    val view = layoutInflater.inflate(R.layout.recyclerview_row_hero, parent, false)
                    ViewHolderHero(view, parent)
                }
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            when (position) {

                0 -> {

                    val holder = holder as ViewHolderPlayer
                    holder.imageViewPlayer.setImageResource(R.drawable.avatar2)
                    holder.textViewName.text = player.name
                    holder.textViewLevel.text = player.level.toString()
                    holder.onClick = {

                        selectedPosition = position
                        onClick(holder, position)
                    }
                }
                else -> {

                    val holder = holder as ViewHolderHero
                    val hero = heroes[position - 1]
                    holder.imageViewHero.setImageResource(hero.picture)
                    holder.textViewName.text = hero.name
                    holder.textViewLevel.text = hero.level.toString()
                    holder.onClick = {

                        selectedPosition = position
                        onClick(holder, position)
                    }
                }
            }
        }

        override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {

            when (holder.adapterPosition) {

                0 -> {

                    val holder = holder as ViewHolderPlayer
                    holder.selected = holder.adapterPosition == selectedPosition
                }
                else -> {

                    val holder = holder as ViewHolderHero
                    holder.selected = holder.adapterPosition == selectedPosition
                }
            }
        }

        override fun getItemViewType(position: Int): Int = if (position == 0) 0 else 1

        override fun getItemCount(): Int = heroes.size + 1 // +1 for player row
    }
}
