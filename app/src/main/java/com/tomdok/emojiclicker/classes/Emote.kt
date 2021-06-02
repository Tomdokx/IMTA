package com.tomdok.emojiclicker.classes

class Emote(_id: Long, _name: String, _maxHp: Double, _picture: Int) {

    var currentHp = _maxHp
    val picture = _picture
    val maxHp = _maxHp
    val name = _name
    val id = _id

    fun getHurt(dps: Double){

        currentHp -= dps
    }
}
