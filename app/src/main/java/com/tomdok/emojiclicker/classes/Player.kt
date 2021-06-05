package com.tomdok.emojiclicker.classes

import java.io.Serializable
import kotlin.random.Random

class Player(_name: String, _level: Int, _tCoins: Int, _dps: Double) {

    var dps = _dps
    var tCoins = _tCoins
    val name = _name
    var level = _level

    fun clickAndDoDps(emote: Emote){

        emote.getHurt(dps)
    }
}