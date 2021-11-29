package pl.kylos.marczuk.pm_fm_test.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Query("Select * FROM data ORDER BY id ASC ")
    fun getAll(): LiveData<List<DataEntity>>

    @Insert
    fun insertAll(vararg data: DataEntity)

    @Query("DELETE FROM data")
    fun clearAll()

    @Query("SELECT * FROM data WHERE id = :id")
    fun findByUserId(id: Int): DataEntity
}