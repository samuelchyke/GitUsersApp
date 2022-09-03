package com.example.gitusers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class CacheGitUser(
    @PrimaryKey
    val id: Int,
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)

fun List<GitUser>.mapToCache(): List<CacheGitUser> {
    return this.map { user ->
        CacheGitUser(
            id = user.id,
            avatar_url = user.avatar_url,
            events_url = user.events_url,
            followers_url = user.followers_url,
            following_url = user.following_url,
            gists_url = user.gists_url,
            gravatar_id = user.gravatar_id,
            html_url = user.html_url,
            login = user.login,
            node_id = user.node_id,
            organizations_url = user.organizations_url,
            received_events_url = user.received_events_url,
            repos_url = user.repos_url,
            site_admin = user.site_admin,
            starred_url = user.starred_url,
            subscriptions_url = user.subscriptions_url,
            type = user.type,
            url = user.url
        )
    }
}