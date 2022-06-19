package com.arbaelbarca.trainingclassintermediate.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arbaelbarca.trainingclassintermediate.db.dao.DaoMahasiswa
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

@Database(entities = [EntityMahasiswa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoMahasiswa(): DaoMahasiswa

    companion object {

        @Volatile
        var InstanceDb: AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            if (InstanceDb == null) {
                synchronized(AppDatabase::class) {
                    InstanceDb = Room
                        .databaseBuilder(
                            context.applicationContext, AppDatabase::class.java,
                            "db_mahasiswa"
                        )
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return InstanceDb as AppDatabase
        }
    }
}