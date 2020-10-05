package com.neu.githubsrepos.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.neu.githubsrepos.R
import com.neu.githubsrepos.github.models.Repository
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val repository: Repository? = getRepository()

        if (repository == null) {
            Toast.makeText(this, "Erro, repositorio inválido", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Glide.with(this).load(repository.owner.avatar_url).placeholder(R.drawable.ic_perfil)
                .into(avatar)

            login.text = repository.owner.login
            description.text = repository.description

            repository.getLanguages(languages, true)

            button.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(repository.html_url)))
            }

            configActionBar()
        }
    }

    private fun configActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true);
    }

    fun getRepository(): Repository? {
        /* Obtem o respositorio atraves do intent */
        if (intent.hasExtra(Repository.EXTRA_KEY)) {
            val serializableExtra = intent.getSerializableExtra(Repository.EXTRA_KEY)
            if (serializableExtra is Repository)
                return serializableExtra
        }
        return null
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}