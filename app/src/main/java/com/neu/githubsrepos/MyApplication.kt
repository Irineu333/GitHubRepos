package com.neu.githubsrepos

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.Stetho
import com.neu.githubsrepos.database.RepoDatabase

open class MyApplication : Application() {
    companion object {
        var database: RepoDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        //Room
        database = RepoDatabase.getInstance(this)

        //Stetho
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}