package com.example.gitusers.model

import com.google.gson.annotations.SerializedName

data class Search(
    val incomplete_results: Boolean,
//    @SerializedName("items")
    val items: List<GitUser>,
    val total_count: Int
)