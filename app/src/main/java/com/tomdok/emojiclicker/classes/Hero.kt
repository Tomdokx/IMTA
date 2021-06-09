package com.tomdok.emojiclicker.classes

import android.media.Image
import java.lang.Math.ceil

class Hero(_name: String, _level: Int, _baseDPS: Double, _picture: Int, _baseUpgradeCost: Int, _price: Int) {

    val picture = _picture
    val baseDPS = _baseDPS
    var level = _level
    val name = _name
    val price = _price
    val baseUpgradeCost = _baseUpgradeCost

    fun doDamage(emote: Emote) {

        emote.getHurt(baseDPS * (1.25 * level))
    }

    fun calculatePrice(): Int {

        if (level == 0) {

            return price
        }

        return ceil(baseUpgradeCost * level * 1.35).toInt()
    }
}