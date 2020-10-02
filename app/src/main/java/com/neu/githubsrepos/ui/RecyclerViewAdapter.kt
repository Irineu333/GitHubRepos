package com.neu.githubsrepos.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.models.Repository

class RecyclerViewAdapter(private val context: Context,  val listaRepos : MutableList<Repository> = mutableListOf()) : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        Log.d("RecyclerView", "create view")
        return Holder(itemView)
    }

    init {
        Log.d("RecyclerView", "count ${listaRepos.size}")
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        //objects
        val repository : Repository = listaRepos.get(position)
        val autor = repository.owner

        //strings
        val userName = repository.name
        val userLogin = autor.login

        //avatar
        //val avatar : Bitmap

        Log.d("RecyclerView", "bind view\n pos $position")
        Log.d("RecyclerView", "repo $userName, $userLogin")
        holder.bind(null, userName, userLogin)
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerView", "getItemCount ${listaRepos.size}")
        return listaRepos.size
    }

    fun setRepositories(listaRepos : MutableList<Repository>)
    {
        this.listaRepos.clear()
        listaRepos.forEach{ this.listaRepos.add(it) }
        notifyDataSetChanged()

        Log.d("RecyclerView", "count ${this.listaRepos.size}")
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        private val userName = itemView.findViewById<TextView>(R.id.user_name)
        private val loginName = itemView.findViewById<TextView>(R.id.login_name)

        /** Vincula as informações nas views corretas*/
        fun bind(avatar: Bitmap?, userName : String, loginName : String)
        {
            this.avatar?.setImageBitmap(avatar)
            this.userName.text = userName
            this.loginName.text = loginName
        }
    }

}
