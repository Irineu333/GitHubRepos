package com.neu.githubsrepos.github

import com.neu.githubsrepos.github.models.Repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    /** repositórios publicos */
    @GET("/repositories")
    fun listPublic() : Call<List<Repository>>

    /** Languages de um repositório */
    @GET("/repos/{login}/{name}/languages")
    fun listLanguages(@Path("login") login : String, @Path("name") name : String) : Call<HashMap<String, Any>>

}