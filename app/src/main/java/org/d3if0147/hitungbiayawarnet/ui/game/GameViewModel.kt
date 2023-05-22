package org.d3if0147.hitungbiayawarnet.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0147.hitungbiayawarnet.R
import org.d3if0147.hitungbiayawarnet.model.Game

class GameViewModel : ViewModel() {
    private val data = MutableLiveData<List<Game>>()

    init {
        data.value = initData()
    }
    private fun initData(): List<Game> {
        return listOf(
            Game("Counter Strike : Global Offensive", "Valve Corporation", R.drawable.cs_go_counter_strike),
            Game("Grand Theft Auto V", "Rockstar Games", R.drawable.grand_theft_auto_v),
            Game("Fortnite", "Epic Games", R.drawable.fortnite),
            Game("FIFA 23", "Electronic Arts", R.drawable.ea_sport_fifa_23),
            Game("Valorant", "RIOT Games", R.drawable.valorant),
            Game("Call Of Duty Warzone", "Activision ", R.drawable.call_of_duty_warzone_game),
            Game("Rust", "Facepunch Studios", R.drawable.rust_video_game),
            Game("Final Fantasy XV", "Square Enix", R.drawable.final_fantasy_xiv_online_game),
            Game("Super Smash Bros Ultimate", "Nintendo", R.drawable.super_smash_bros_ultimate),
            )
    }
    fun getData(): LiveData<List<Game>> = data
}