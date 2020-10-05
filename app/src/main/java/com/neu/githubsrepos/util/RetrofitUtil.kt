package com.neu.githubsrepos.util

import android.util.Log
import android.widget.TextView
import com.neu.githubsrepos.github.GitHubService
import com.neu.githubsrepos.github.models.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var retrofitUtil: RetrofitUtil? = null
    get() {
        return if (field == null) createRetroftUtil() else field
    }

private fun createRetroftUtil(baseUrl: String = "https://api.github.com/"): RetrofitUtil {
    Log.d("RetrofitUtil", "createRetroftUtil")
    return RetrofitUtil(baseUrl)
}

class RetrofitUtil(
    baseUrl: String,
) {

    val gitHubService: GitHubService = createGitHubService(baseUrl)

    fun listLanguages(
        repository: Repository,
        textView: TextView? = null,
        lista: MutableList<String>? = null,
        callback: Callback<HashMap<String, Any>>? = null,
        emLista: Boolean = false
    ) {

        /* Obter a lista de linguagens de um repositorio */

        val callListLanguages =
            gitHubService.listLanguages(repository.owner.login, repository.name)

        callListLanguages.enqueue(callback ?: object : Callback<HashMap<String, Any>> {
            override fun onResponse(
                call: Call<HashMap<String, Any>>,
                response: Response<HashMap<String, Any>>
            ) {
                val body = response.body()
                Log.d("Retrofit", "languages, onResponse: $body")

                val listaKeys: MutableList<String> = ArrayList()
                body?.forEach { listaKeys.add(it.key) }

                if (textView != null) {
                    formatar(textView, listaKeys, emLista)
                }

                if (lista != null) {
                    lista.clear()
                    for (i in 0 until listaKeys.size) {
                        lista.add(listaKeys.get(i))
                    }
                }
            }

            override fun onFailure(call: Call<HashMap<String, Any>>, t: Throwable) {
                Log.d("Retrofit", "onFailure, languages ${t.message}")
            }
        })

    }

    private fun createGitHubService(baseUrl: String): GitHubService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GitHubService::class.java)
    }

    fun formatar(textView: TextView, listaKeys: MutableList<String>, emLista: Boolean = false) {
        var final = ""
        var languages = ""
        if (emLista) {
            listaKeys.forEach { languages += it + "\n" }
        } else {
            val qtd = if (listaKeys.size < 3) listaKeys.size else {
                final = ".."
                3
            }
            for (i in 0 until qtd) {
                languages += "${listaKeys.get(i)}${if (i == qtd - 1) "." else ", "}"
            }
        }
        val texto = languages + final;
        textView.text = texto
    }
}