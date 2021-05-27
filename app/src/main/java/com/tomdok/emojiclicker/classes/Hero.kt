package com.tomdok.emojiclicker.classes

import android.media.Image

class Hero(id:Int, level:Int, baseDPS:Double, picture: Image) {
    private val picture = picture
    private val baseDPS = baseDPS
    private var level = level
    private val id = id

    fun getIdHero(): Int {return this.id}
    fun getLevelHero(): Int {return this.level}
    fun setLevelHero(value:Int) {this.level = value}
    fun getBaseDPSHero(): Double {return this.baseDPS}

}