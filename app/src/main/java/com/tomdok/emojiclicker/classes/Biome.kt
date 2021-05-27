package com.tomdok.emojiclicker.classes

import android.media.Image

class Biome(name:String, background:Image) {
    private val background = background
    private val name = name

    fun getNameBiome(): String {return this.name}
    fun getBackgroundBiome(): Image {return this.background}
}