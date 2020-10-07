package com.neu.githubsrepos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neu.githubsrepos.github.models.Owner
import com.neu.githubsrepos.github.models.Repository

@Database(entities = [Repository::class, Owner::class], version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {
    abstract val repoDatabaseDao :RepoDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: RepoDatabase? = null
        fun getInstance(context : Context) : RepoDatabase
        {
            synchronized(this) /* para evitar duplicação */ {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RepoDatabase::class.java,
                        "repo_history_database"
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