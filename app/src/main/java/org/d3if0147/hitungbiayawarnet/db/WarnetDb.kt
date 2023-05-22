package org.d3if0147.hitungbiayawarnet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WarnetEntity::class], version = 1, exportSchema = false)
abstract class WarnetDb : RoomDatabase(){

    abstract val dao: WarnetDao

    companion object{

        @Volatile
        private var INSTANCE: WarnetDb? = null

        fun getInstance(context: Context): WarnetDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WarnetDb::class.java,
                        "Warnet.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}