package com.tomdok.emojiclicker.classes

import android.media.Image

class Emote(id:Int, name:String, maxHp:Double, picture:Image) {
    private var currentHp = maxHp

    private val picture = picture

    private val maxHp = maxHp

    private val name = name

    private val id = id

    fun getNameEmote(): String {return this.name}

    fun getIdEmote(): Int {return this.id}

    fun getMaxHpEmote(): Double {return this.maxHp}

    fun getCurrentHpEmote(): Double {return this.currentHp}

    fun setCurrentHpEmote(value: Double) {this.currentHp = value}

    fun getPictureEmote(): Image {return this.picture}

}
