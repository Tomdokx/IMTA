package database

import androidx.room.TypeConverter
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun toTimestamp(value: Long?): Timestamp? {

        return value?.let { Timestamp(it) }
    }

    @TypeConverter
    fun fromTimestamp(value: Timestamp?): Long? {

        return value?.time?.toLong()
    }
}