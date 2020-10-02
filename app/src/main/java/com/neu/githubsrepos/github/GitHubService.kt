package com.neu.githubsrepos.github

import com.neu.githubsrepos.github.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    /** Restona os repositórios publicos, quantidade=100 */
    @GET("/repositories")
    fun listPublic() : Call<List<Repository>>
}