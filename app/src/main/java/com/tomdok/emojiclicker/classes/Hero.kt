package com.tomdok.emojiclicker.classes

import android.media.Image
import com.tomdok.emojiclicker.Abilities.Ability
import java.lang.Math.ceil

class Hero(_name: String, _level: Int, _baseDPS: Double, _picture: Int, _baseUpgradeCost: Int, _price: Int, _ability: Ability) {

    val picture = _picture
    var baseDPS = _baseDPS
    var level = _level
    val name = _name
    val price = _price
    val baseUpgradeCost = _baseUpgradeCost
    val ability = _ability

    fun doDamage(emote: Emote) {

        emote.getHurt(baseDPS * (1.25 * level))
    }

    fun calculatePrice(): Int {

        if (level == 0) {

            return price
        }

        return ceil(baseUpgradeCost * level * 1.35).toInt()
    }

    fun doAbility(player: Player, emote: Emote, refreshHPBar: () -> Unit) {

       return ability.doEffects(player, this, emote, refreshHPBar)
    }
}