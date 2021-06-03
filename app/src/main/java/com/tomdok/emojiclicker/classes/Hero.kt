package com.tomdok.emojiclicker.classes

import android.media.Image

class Hero(_id: Long, _name: String, _level: Int, _baseDPS: Double, _picture: Int) {

    val picture = _picture
    val baseDPS = _baseDPS
    var level = _level
    val id = _id
    val name = _name

    fun doDamage(emote: Emote) {

        emote.getHurt(baseDPS * (1.25 * level))
    }
}