package org.d3if0147.hitungbiayawarnet.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warnet")
data class WarnetEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jam: Int,
    var tipe: String
)