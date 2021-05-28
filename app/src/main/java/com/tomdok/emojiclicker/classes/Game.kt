package com.tomdok.emojiclicker.classes

import java.sql.Timestamp

class Game(timeStart:Timestamp, timeEnd:Timestamp, level:Int) {
    private var level = level
    private val timeEnd = timeEnd
    private val timeStart = timeStart

    fun getLevelGame(): Int {return this.level}
    fun getTimeStartGame(): Timestamp {return this.timeStart}
    fun getTimeEndGame(): Timestamp {return this.timeEnd}
    fun setLevelGame(value:Int) {this.level = value}

}