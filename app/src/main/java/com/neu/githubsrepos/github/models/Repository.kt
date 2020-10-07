package com.neu.githubsrepos.github.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Repository(

    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val node_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("owner") val owner: Owner /* objeto, informações proprietário */,
    @SerializedName("private") val private: Boolean,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("archive_url") val archive_url: String,
    @SerializedName("assignees_url") val assignees_url: String,
    @SerializedName("blobs_url") val blobs_url: String,
    @SerializedName("branches_url") val branches_url: String,
    @SerializedName("collaborators_url") val collaborators_url: String,
    @SerializedName("comments_url") val comments_url: String,
    @SerializedName("commits_url") val commits_url: String,
    @SerializedName("compare_url") val compare_url: String,
    @SerializedName("contents_url") val contents_url: String,
    @SerializedName("contributors_url") val contributors_url: String,
    @SerializedName("deployments_url") val deployments_url: String,
    @SerializedName("downloads_url") val downloads_url: String,
    @SerializedName("events_url") val events_url: String,
    @SerializedName("forks_url") val forks_url: String,
    @SerializedName("git_commits_url") val git_commits_url: String,
    @SerializedName("git_refs_url") val git_refs_url: String,
    @SerializedName("git_tags_url") val git_tags_url: String,
    @SerializedName("git_url") val git_url: String,
    @SerializedName("issue_comment_url") val issue_comment_url: String,
    @SerializedName("issue_events_url") val issue_events_url: String,
    @SerializedName("issues_url") val issues_url: String,
    @SerializedName("keys_url") val keys_urls_url: String,
    @SerializedName("labels_url") val labels_url: String,
    @SerializedName("languages_url") val languages_url: String,
    @SerializedName("merges_url") val merges_url: String,
    @SerializedName("milestones_url") val milestones_url: String,
    @SerializedName("notifications_url") val notifications_url: String,
    @SerializedName("pulls_url") val pulls_url: String,
    @SerializedName("releases_url") val releases_url: String,
    @SerializedName("ssh_url") val ssh_url: String,
    @SerializedName("stargazers_url") val stargazers_url: String,
    @SerializedName("statuses_url") val statuses_url: String,
    @SerializedName("subscribers_url") val subscribers_url: String,
    @SerializedName("subscription_url") val subscription_url: String,
    @SerializedName("tags_url") val tags_url: String,
    @SerializedName("teams_url") val teams_url: String,
    @SerializedName("trees_url") val trees_url: String
) : Serializable {
    /*
    private var languages : MutableList<String>? = null

    companion object {
        const val EXTRA_KEY: String = "repository"
    }

    fun getLanguages(textView: TextView, emLista: Boolean = false) {

        if(languages == null)
        {
            languages = ArrayList()
            retrofitUtil?.listLanguages(this, textView, languages, emLista = emLista)
        } else
            retrofitUtil?.formatar(textView, languages!!, emLista)
    }
     */
}