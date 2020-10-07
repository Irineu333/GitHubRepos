package com.neu.githubsrepos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.GitHubService
import com.neu.githubsrepos.github.models.Repository
import com.neu.githubsrepos.ui.MainActivity
import com.neu.githubsrepos.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(), RecyclerViewAdapter.OnClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val gitHubService = createGitHubService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter = RecyclerViewAdapter(context = requireActivity(), listener = this)

        configRecyclerView()



        //botões config


        //carregar repositórios públicos
        listPublic()
    }
    companion object {

        fun newInstance() = MainFragment()

    }


    private fun createGitHubService(): GitHubService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GitHubService::class.java)
    }

    private fun configRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerViewAdapter
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

                if (body == null) {
                    val message = response.message()
                    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    Log.d("Retrofit", "onResponse, body = null, message: $message")
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Log.d("Retrofit", "onFailure: ${t.message}")
            }

        })
    }

    private fun navigateTo(repository: Repository) {
        val actionMainFragmentToRepositoryFragment =
            MainFragmentDirections.actionMainFragmentToRepositoryFragment(repository)

        findNavController(this).navigate(actionMainFragmentToRepositoryFragment)
    }

    override fun onClick(data: Any) {

        if (data is Repository) {
            navigateTo(data)
        }

    }
}