package com.neu.githubsrepos.github.models


import android.widget.TextView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.neu.githubsrepos.util.retrofitUtil
import java.io.Serializable

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity(tableName = "repository_table")
class Repository(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int = -1,
    @SerializedName("node_id") var node_id: String = "",
    @ColumnInfo(name = "repository_name")
    @SerializedName("name") var name: String = "",
    @SerializedName("full_name") var full_name: String = "",
//    @ColumnInfo(name = "author_object")
    @Ignore
    @SerializedName("owner") var owner: Owner? = null /* objeto, informações proprietário */,
    @SerializedName("private") var private: Boolean? = null,
    @SerializedName("html_url") var html_url: String = "",
    @ColumnInfo(name = "description")
    @SerializedName("description") var description: String? = "",
    @SerializedName("fork") var fork: Boolean? = null,
    @SerializedName("url") var url: String = "",
    @SerializedName("archive_url") var archive_url: String = "",
    @SerializedName("assignees_url") var assignees_url: String = "",
    @SerializedName("blobs_url") var blobs_url: String = "",
    @SerializedName("branches_url") var branches_url: String = "",
    @SerializedName("collaborators_url") var collaborators_url: String = "",
    @SerializedName("comments_url") var comments_url: String = "",
    @SerializedName("commits_url") var commits_url: String = "",
    @SerializedName("compare_url") var compare_url: String = "",
    @SerializedName("contents_url") var contents_url: String = "",
    @SerializedName("contributors_url") var contributors_url: String = "",
    @SerializedName("deployments_url") var deployments_url: String = "",
    @SerializedName("downloads_url") var downloads_url: String = "",
    @SerializedName("events_url") var events_url: String = "",
    @SerializedName("forks_url") var forks_url: String = "",
    @SerializedName("git_commits_url") var git_commits_url: String = "",
    @SerializedName("git_refs_url") var git_refs_url: String = "",
    @SerializedName("git_tags_url") var git_tags_url: String = "",
    @SerializedName("git_url") var git_url: String = "",
    @SerializedName("issue_comment_url") var issue_comment_url: String = "",
    @SerializedName("issue_events_url") var issue_events_url: String = "",
    @SerializedName("issues_url") var issues_url: String = "",
    @SerializedName("keys_url") var keys_urls_url: String = "",
    @SerializedName("labels_url") var labels_url: String = "",
    @SerializedName("languages_url") var languages_url: String = "",
    @SerializedName("merges_url") var merges_url: String = "",
    @SerializedName("milestones_url") var milestones_url: String = "",
    @SerializedName("notifications_url") var notifications_url: String = "",
    @SerializedName("pulls_url") var pulls_url: String = "",
    @SerializedName("releases_url") var releases_url: String = "",
    @SerializedName("ssh_url") var ssh_url: String = "",
    @SerializedName("stargazers_url") var stargazers_url: String = "",
    @SerializedName("statuses_url") var statuses_url: String = "",
    @SerializedName("subscribers_url") var subscribers_url: String = "",
    @SerializedName("subscription_url") var subscription_url: String = "",
    @SerializedName("tags_url") var tags_url: String = "",
    @SerializedName("teams_url") var teams_url: String = "",
    @SerializedName("trees_url") var trees_url: String = ""
) : Serializable  {
    @Ignore
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
}