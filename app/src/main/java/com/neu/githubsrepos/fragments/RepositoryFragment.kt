package com.neu.githubsrepos.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.models.Repository
import com.neu.githubsrepos.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_repository.*

class RepositoryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setFragment(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = RepositoryFragmentArgs.fromBundle(requireArguments()).repository
        Log.d("Arguments", repository.name)

        Glide.with(requireActivity()).load(repository.owner.avatar_url).placeholder(R.drawable.ic_perfil)
            .into(avatar)

        login.text = repository.owner.login
        description.text = repository.description

        //repository.getLanguages(languages, true)

        button.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(repository.html_url)))
        }
    }

    private fun navigateTo() {
        NavHostFragment.findNavController(this).navigate(RepositoryFragmentDirections.actionRepositoryFragmentToMainFragment())
    }

    companion object {
        fun newInstance() = RepositoryFragment()
    }
}