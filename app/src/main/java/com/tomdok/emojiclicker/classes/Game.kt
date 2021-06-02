package com.tomdok.emojiclicker.classes

import java.sql.Timestamp

class Game(_timeStart: Timestamp, _timeEnd: Timestamp, _level: Int) {

    var level = _level
    var timeEnd = _timeEnd
    val timeStart = _timeStart
}