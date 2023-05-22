package org.d3if0147.hitungbiayawarnet.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0147.hitungbiayawarnet.db.WarnetDao

class HistoriViewModel(private val db: WarnetDao) : ViewModel() {
    val data = db.getLastWarnet()

    fun hapusdata(id: Long) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteHistory(id)
        }
}
}