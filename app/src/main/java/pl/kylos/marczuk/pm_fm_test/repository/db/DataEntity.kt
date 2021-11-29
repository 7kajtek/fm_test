package pl.kylos.marczuk.pm_fm_test.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey val id: Int,
    val description: String,
    val imageUrl: String,
    val modificationDate: String,
    val title: String
)
