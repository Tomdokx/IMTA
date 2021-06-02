package com.tomdok.emojiclicker.classes

import java.io.Serializable
import kotlin.random.Random

class Player(_name: String, _tCoins: Int, _dps: Double): Serializable {

    var dps = _dps
    var tCoins = _tCoins
    val name = _name

    fun clickAndDoDps(emote: Emote){

        emote.getHurt(dps)
    }
}