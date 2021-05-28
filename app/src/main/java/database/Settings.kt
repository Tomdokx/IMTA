package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "table_settings", foreignKeys = arrayOf(
    ForeignKey(entity = Player::class,
        parentColumns = arrayOf("name"),
        childColumns = arrayOf("player_name"),
        onDelete = ForeignKey.CASCADE)
))
data class Settings (

    @PrimaryKey val id: Long? = 0L,
    @ColumnInfo val animations_on: Boolean,
    @ColumnInfo(index = true) val player_name: String
)