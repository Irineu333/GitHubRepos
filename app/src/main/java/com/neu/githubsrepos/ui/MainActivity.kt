package com.neu.githubsrepos.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.GitHubService
import com.neu.githubsrepos.github.models.Repository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val gitHubService = createGitHubService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configActionBar()
        recyclerViewAdapter = RecyclerViewAdapter(this, listener =  this)//retrofit config

        configRecyclerView()

        //botões config
        btnPerfil.setOnClickListener(onItemToolbarClick)
        btnSearch.setOnClickListener(onItemToolbarClick)

        //carregar repositórios públicos
        listPublic()

    }

    private fun createGitHubService(): GitHubService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GitHubService::class.java)
    }

    private fun configRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerViewAdapter
    }

    private val onItemToolbarClick = OnClickListener {

        when (it.id) {

            R.id.btnPerfil -> {
                makeText(this, "Perfil", Toast.LENGTH_LONG).show()
            }
            R.id.btnSearch -> {
                makeText(this, "Search", Toast.LENGTH_LONG).show()

                val searchView = SearchView(this)
                toolbar.addView(searchView)
            }
        }
    }

    private fun listPublic() {

        val callbackListPublic = gitHubService.listPublic()

        callbackListPublic.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {

                val body = response.body()

                Log.d("Retrofit", "onResponse")
                body?.forEach {
                    Log.d("name", it.name)
                }
                Log.d("Retrofit", "Total: ${body?.size}")

                //setando no RecyclerView
                val repositories: MutableList<Repository>? = body as? MutableList
                recyclerViewAdapter.setRepositories(repositories ?: mutableListOf())

                if(body == null)
                {
                    val message = response.message()
                    makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                    Log.d("Retrofit", "onResponse, body = null, message: $message")
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Log.d("Retrofit", "onFailure: ${t.message}")
            }

        })
    }

    private fun configActionBar() {
        /** Configura a toolbar para que tenha o comportamente esperado */
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //desativar o titulo padrão
    }

    override fun onClick(data: Any) {

        if (data is Repository) {
            val intent = Intent(this, RepositoryActivity::class.java)
            intent.putExtra(Repository.EXTRA_KEY, data)
            startActivity(intent)
        }

    }
}