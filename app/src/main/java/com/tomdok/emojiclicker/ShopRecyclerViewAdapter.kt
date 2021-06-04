package com.tomdok.emojiclicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player

class ShopRecyclerViewAdapter(
    private val context: Context,
    private val heroes: List<Hero>,
    private val player: Player,
    private val onClick: (holder: RecyclerView.ViewHolder, selectedPosition: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selectedPosition: Int = 0

    inner class ViewHolderPlayer(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {

        val imageViewPlayer: ImageView = view.findViewById(R.id.shop_row_player_imageView)
        val textViewName: TextView = view.findViewById(R.id.shop_row_player_textViewName)
        val textViewLevel: TextView = view.findViewById(R.id.shop_row_player_textViewLevel)

        var selected: Boolean = false
            set(value) {

                field = value
                if (value) {

                    itemView.background.alpha = 255
                } else {

                    itemView.background.alpha = 0
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

        val imageViewHero: ImageView = view.findViewById(R.id.shop_row_hero_imageView)
        val textViewName: TextView = view.findViewById(R.id.shop_row_hero_textViewName)
        val textViewLevel: TextView = view.findViewById(R.id.shop_row_player_textViewLevel)

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