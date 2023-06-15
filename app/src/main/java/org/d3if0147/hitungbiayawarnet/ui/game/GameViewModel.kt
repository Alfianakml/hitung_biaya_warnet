package org.d3if0147.hitungbiayawarnet.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0147.hitungbiayawarnet.model.Game
import org.d3if0147.hitungbiayawarnet.network.ApiStatus
import org.d3if0147.hitungbiayawarnet.network.GameApi

class GameViewModel : ViewModel() {
    private val data = MutableLiveData<List<Game>>()
    private val status = MutableLiveData<ApiStatus>()


    init {

        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(GameApi.service.getGame())
                status.postValue(ApiStatus.SUCCESS)

            } catch (e: Exception) {
                Log.d("GameViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Game>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}