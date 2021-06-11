package com.tomdok.emojiclicker.Abilities

import com.tomdok.emojiclicker.classes.Emote
import com.tomdok.emojiclicker.classes.Hero
import com.tomdok.emojiclicker.classes.Player

class AbilityQuickDamage(_damage: Double, _picture: Int): Ability {

    var damage = _damage
    override val PICTURE = _picture
    override val DESCRIPTION: String?
        get() = "Knight will take another sword and throw that into the emote to once give him big damage deal."

    override fun doEffects(player: Player, hero: Hero, emote: Emote, refreshHPBar: () -> Unit) {

        emote.getHurt(damage)
    }
}