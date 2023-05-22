package org.d3if0147.hitungbiayawarnet.model


import org.d3if0147.hitungbiayawarnet.db.WarnetEntity
import kotlin.random.Random



fun WarnetEntity.hitungPemakaian(): HasilHitung {
    fun generateRandomUser(length: Int): String {
        val charPool: List<Char> =
            ('A'..'Z') + ('A'..'Z') // Define the characters to choose from
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun generateRandomPassword(length: Int): String {
        val charPool: List<Char> =
            ('A'..'Z') + ('0'..'9') // Define the characters to choose from
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    val jam = jam
    val tipekomputer = if (tipe.equals("Regular", ignoreCase = true)) {
        jam * 5000
    } else if (tipe.equals("VIP", ignoreCase = true)) {
        jam * 7000
    } else if (tipe.equals("VVIP", ignoreCase = true)) {
        jam * 9000
    } else {
        0
    }

    val user = generateRandomUser(4)
    val pass = generateRandomPassword(6)

    return HasilHitung(user, pass, jam, tipekomputer)
}