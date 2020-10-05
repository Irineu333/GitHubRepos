package com.neu.githubsrepos.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.models.Repository

class RecyclerViewAdapter(
    val context: Context,
    private var listaRepos: MutableList<Repository> = mutableListOf(),
    val listener: OnClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        Log.d("RecyclerView", "create view")
        return Holder(itemView)
    }

    init {
        Log.d("RecyclerView", "count ${listaRepos.size}")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //objects
        val repository: Repository = listaRepos[position]
        val autor = repository.owner

        //strings
        val userName = repository.name
        val userLogin = autor.login

        holder.languages.text = ""
        repository.getLanguages(holder.languages)

        //avatar config using Glide
        Glide.with(context).load(repository.owner.avatar_url).placeholder(R.drawable.ic_perfil)
            .into(holder.avatar)

        Log.d("RecyclerView", "bind view pos $position")
        Log.d("RecyclerView", "repo $userName, $userLogin")


        holder.itemView.setOnClickListener {

            listener.onClick(repository)
        }

        holder.bind(userName, userLogin)
    }

    override fun getItemCount(): Int {
        return listaRepos.size
    }

    fun setRepositories(listaRepos: MutableList<Repository>) {
        this.listaRepos.clear()
        this.listaRepos = listaRepos
        notifyDataSetChanged()

        Log.d("RecyclerView", "count ${this.listaRepos.size}")
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var onClickListener : View.OnClickListener? = null
        private var position : Int? = null

        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val languages = itemView.findViewById<TextView>(R.id.languages)

        private val userName = itemView.findViewById<TextView>(R.id.user_name)
        private val login = itemView.findViewById<TextView>(R.id.login)

        /** Vincula as informações nas views corretas*/
        fun bind(userName: String, login: String) {
            this.userName.text = userName
            this.login.text = login
        }

    }


    interface  OnClickListener {

        fun onClick(data:Any)
    }

}
