package com.egabruskiy.githubsearcher.data.model

import com.google.gson.annotations.SerializedName


//имя рпео
//автор(логин и аватар)
//описание
//количество форк
//количество стар
//дата



data class SearchResponse(
    @SerializedName("total_count")
    val total_count: Long? = null,
    @SerializedName("items")
    val repositories: List<RepositoryResponse>
)



//val repositories: List<GitRepoModel> = emptyList())

data class RepositoryResponse(
    @SerializedName("name")
    val repoName: String? = null,
//    @SerializedName("html_url")
//    val htmlUrl: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("owner")
    val owner: Owner? = null,
    @SerializedName("updated_at")
    val updatedAt: String?=null ,
    @SerializedName("forks_count")
    val forks: String?=null,
    @SerializedName("stargazers_count")
    val stars: String?=null,
    @SerializedName("description")
    val description:String? = null
//    ,
//    val avatarUrl: String? = owner?.avatarUrl,
//    val ownerLogin: String? = owner?.login
)


data class Owner(
    @SerializedName("avatar_url") val avatarUrl:String? = null,
    @SerializedName("login") val login:String? = null
)

