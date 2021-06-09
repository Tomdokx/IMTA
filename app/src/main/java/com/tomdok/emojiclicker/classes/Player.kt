package com.tomdok.emojiclicker.classes

import java.io.Serializable
import kotlin.math.ceil
import kotlin.random.Random

class Player(_name: String, _level: Int, _tCoins: Int, _dps: Double, _baseUpgradeCost: Int) {

    var dps = _dps
    var tCoins = _tCoins
    val name = _name
    var level = _level
    var baseUpgradeCost = _baseUpgradeCost

    fun clickAndDoDps(emote: Emote) {

        emote.getHurt(dps)
    }

    fun calculatePrice() :Int {

        return (baseUpgradeCost * level)
    }
}