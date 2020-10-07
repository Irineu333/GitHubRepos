package com.neu.githubsrepos.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.neu.githubsrepos.R
import com.neu.githubsrepos.fragments.MainFragment
import com.neu.githubsrepos.fragments.RepositoryFragment
import com.neu.githubsrepos.fragments.RepositoryFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    fun setFragment(fragment: Fragment) {
        this.fragment = fragment
        Log.d("MainActivity", "setFragment")

        if (fragment is RepositoryFragment || fragment !is MainFragment) {
            Log.d("MainActivity", "cast ${fragment as RepositoryFragment}")

            search_field.setOnQueryTextListener(null)
            search_field.onActionViewCollapsed()
            //
            search_bar.visibility = View.GONE

            btnVoltar.visibility = View.VISIBLE
            btnPerfil.visibility = View.GONE
        } else {
            btnVoltar.visibility = View.GONE
            btnPerfil.visibility = View.VISIBLE
        }
        configSearchBar()
    }

    override fun onSupportNavigateUp() = findNavController(R.navigation.my_navigation).navigateUp()

    private val onItemToolbarClick: View.OnClickListener
        get() {
            fechar.setOnClickListener {
                search_bar.visibility = View.GONE
                search_field.onActionViewCollapsed()
            }
            return View.OnClickListener {

                when (it.id) {

                    R.id.btnPerfil -> {
                        Toast.makeText(this, "Perfil", Toast.LENGTH_LONG).show()
                    }
                    R.id.btnSearch -> {
                        Toast.makeText(this, "Search", Toast.LENGTH_LONG).show()

                        if (fragment is RepositoryFragment)
                            actionRepositoryToMain()

                        search_bar.visibility = View.VISIBLE
                        search_field.onActionViewExpanded()
                    }
                }
            }
        }

    private fun configSearchBar() {
        if (fragment is MainFragment) {
            search_field.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    (fragment as MainFragment).recyclerViewAdapter.filter(newText ?: "")
                    return true
                }
            })
            search_field.queryHint = getString(R.string.search_hint)
            btnVoltar.setOnClickListener(null)
        } else {
            btnVoltar.setOnClickListener {
                actionRepositoryToMain()
            }
        }
        btnPerfil.setOnClickListener(onItemToolbarClick)
        btnSearch.setOnClickListener(onItemToolbarClick)
    }

    private fun actionRepositoryToMain() {
        NavHostFragment.findNavController(fragment as RepositoryFragment)
            .navigate(RepositoryFragmentDirections.actionRepositoryFragmentToMainFragment())
    }

}