package org.d3if0147.hitungbiayawarnet.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "Alfianakml/json-mopro/game/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface GameApiService {
    @GET("game.json")
    suspend fun getGame(): String
}
object GameApi {
    val service: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}