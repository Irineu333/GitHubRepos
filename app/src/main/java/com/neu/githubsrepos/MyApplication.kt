package com.neu.githubsrepos

import android.app.Application
import com.facebook.stetho.Stetho

open class MyApplication : Application() {
    companion object {
        //var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        //Room
        //database = Room.databaseBuilder(this, AppDataBase::class.java, "my-db").allowMainThreadQueries().build()

        //Stetho
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}