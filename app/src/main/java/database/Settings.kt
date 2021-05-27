package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "table_settings")
data class Settings (

    @PrimaryKey(autoGenerate = true) val id: Long? = 0L,
    @ColumnInfo(name = "animations") val animations: Boolean,
    @ColumnInfo(index = true) val player_name: String
)