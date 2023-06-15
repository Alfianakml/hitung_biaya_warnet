package org.d3if0147.hitungbiayawarnet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0147.hitungbiayawarnet.model.Game
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "Alfianakml/game-api/master/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GameApiService {
    @GET("game-api.json")
    suspend fun getGame(): List<Game>
}

object GameApi {
    val service: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
    fun getGameUrl(imageResId: String): String {
        return "$BASE_URL$imageResId.png"
    }
}