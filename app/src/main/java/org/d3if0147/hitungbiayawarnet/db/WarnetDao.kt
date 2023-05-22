package org.d3if0147.hitungbiayawarnet.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WarnetDao{
    @Insert
    fun insert(warnet: WarnetEntity)

    @Query("SELECT * FROM warnet ORDER BY id DESC")
    fun getLastWarnet(): LiveData<List<WarnetEntity?>>

    @Query("DELETE FROM warnet")
    fun clearData()

    @Query("DELETE FROM warnet WHERE id = :id")
    fun deleteHistory(id: Long)
}