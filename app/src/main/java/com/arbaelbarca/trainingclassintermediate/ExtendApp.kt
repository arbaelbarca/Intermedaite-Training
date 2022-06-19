package com.arbaelbarca.trainingclassintermediate

import android.app.Application
import com.arbaelbarca.trainingclassintermediate.db.AppDatabase

class ExtendApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase.getDatabaseInstance(this)
    }
}