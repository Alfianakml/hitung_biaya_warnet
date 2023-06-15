package org.d3if0147.hitungbiayawarnet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0147.hitungbiayawarnet.db.WarnetDao
import org.d3if0147.hitungbiayawarnet.db.WarnetEntity
import org.d3if0147.hitungbiayawarnet.model.HasilHitung
import org.d3if0147.hitungbiayawarnet.model.Warnet
import kotlin.random.Random

class MainViewModel(private val db: WarnetDao): ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung>()

    fun tampungWarnet(jam: Int, tipe: String){
        val hitungWarnet = Warnet (
            jam = jam,
            tipe = tipe)
        hasilHitung.value = hitungWarnet.hitungPemakaian()
    }

    fun Warnet.hitungPemakaian(): HasilHitung{
        fun generateRandomUser(length: Int): String {
            val charPool: List<Char> =
                ('A'..'Z') + ('A'..'Z')
            return (1..length)
                .map { Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        }
        fun generateRandomPassword(length: Int): String {
            val charPool: List<Char> =
                ('A'..'Z') + ('0'..'9')
            return (1..length)
                .map { Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataWarnet = WarnetEntity(
                    jam = jam,
                    tipe = tipe
                )
                db.insert(dataWarnet)
            }
        }
        val jam = jam
        val tipekomputer = if (tipe.equals("Regular" , ignoreCase = true)) {
            jam * 5000
        } else if (tipe.equals("VIP" , ignoreCase = true)) {
            jam * 7000
        } else if (tipe.equals("VVIP" , ignoreCase = true)) {
            jam * 9000
        } else {
            0
        }

        val user = generateRandomUser(4)
        val pass = generateRandomPassword(6)

        return HasilHitung(user, pass, jam, tipekomputer)


    }

    fun getUserPassWarnet(): LiveData<HasilHitung?> = hasilHitung
}