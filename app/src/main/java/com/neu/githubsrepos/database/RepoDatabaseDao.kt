package com.neu.githubsrepos.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.neu.githubsrepos.github.models.Owner
import com.neu.githubsrepos.github.models.Repository

@Dao
interface RepoDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository, owner: Owner)

    @Query("SELECT * from repository_table WHERE id = :id")
    fun getRepo(id: Int): Repository?

    @Query("SELECT * from repository_table WHERE full_name = :login + '_' + :name")
    fun getRepo(login : String, name : String): Repository?

    @Query("SELECT * from author_table WHERE login = :login")
    fun getOwner(login : String): Owner?

    @Query("DELETE FROM repository_table")
    fun clear()

    @Query("DELETE FROM repository_table WHERE id = :id")
    fun delete(id : Int)

    @Query("DELETE FROM repository_table WHERE full_name = :login + '_' + :name")
    fun delete(login : String, name : String)

    @Query("SELECT * FROM repository_table")
    fun getRepoAll(): List<Repository>

}