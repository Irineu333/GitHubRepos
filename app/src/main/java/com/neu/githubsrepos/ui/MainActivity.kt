package com.neu.githubsrepos.ui

import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.GitHubService
import com.neu.githubsrepos.github.models.Repository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val recyclerViewAdapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configActionBar(toolbar)
        configRecyclerView(recyclerView)

        //retrofit config
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //gitHub interface
        val gitHubService = retrofit.create(GitHubService::class.java)

        //botões config
        btnPerfil.setOnClickListener(onItensToolbarClick)
        btnSearch.setOnClickListener(onItensToolbarClick)

        //carregar repositórios públicos
        listPublic(gitHubService)

    }

    private fun configRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerViewAdapter
    }

    private val onItensToolbarClick = OnClickListener {

        when (it.id) {

            R.id.btnPerfil -> {

            }
            R.id.btnSearch -> {

            }
        }
    }

    private fun listPublic(gitHubService: GitHubService) {

        val callBackListPublic = gitHubService.listPublic()

        callBackListPublic.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                Log.d("Retrofit", "onResponse")
                var qtd = 0
                response.body()?.forEach {
                    Log.d("name", it.name)
                    qtd++
                }
                Log.d("Retrofit", "Total: $qtd")

                //setando no RecyclerView
                val repositories: MutableList<Repository>? = response.body() as? MutableList
                recyclerViewAdapter.setRepositories(repositories ?: mutableListOf())
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Log.d("Retrofit", "onFailure: ${t.message}")
            }

        })
    }

    private fun configActionBar(toolbar: Toolbar) {
        /** Configura a toolbar para que tenha o comportamente esperado */
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //desativar o titulo padrão
    }
}