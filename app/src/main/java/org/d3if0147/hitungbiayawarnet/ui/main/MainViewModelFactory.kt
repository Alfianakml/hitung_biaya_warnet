package org.d3if0147.hitungbiayawarnet.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0147.hitungbiayawarnet.db.WarnetDao

class MainViewModelFactory(private val db:WarnetDao): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
    }
}