package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player

interface Ability {

    val DESCRIPTION:String?
    val PICTURE:Int?

    fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit)
}