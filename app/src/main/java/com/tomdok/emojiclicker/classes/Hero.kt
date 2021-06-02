package com.tomdok.emojiclicker.classes

import android.media.Image

class Hero(_id: Int, _level: Int, _baseDPS: Double, _picture: Image) {

    val picture = _picture
    val baseDPS = _baseDPS
    var level = _level
    val id = _id


    fun doDamage(emote: Emote){

      emote.getHurt(baseDPS * (1.25 * level))
    }
}