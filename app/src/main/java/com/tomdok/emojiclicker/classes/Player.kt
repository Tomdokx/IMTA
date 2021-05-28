package com.tomdok.emojiclicker.classes

class Player(name:String, tCoins:Int, dps:Double) {
    private var dps = dps
    private var tCoins = tCoins
    private val name = name

    fun getNamePlayer(): String {return this.name}
    fun getTCoinsPlayer(): Int {return this.tCoins}
    fun getDPSPlayer(): Double {return this.dps}
    fun setTCoinsPlayer(value:Int) {this.tCoins = value}
}