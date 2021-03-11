package com.egabruskiy.githubsearcher.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//имя рпео
//автор(логин и аватар)
//описание
//количество форк
//количество стар
//дата

@Entity(tableName = "gitmodel")
@Parcelize
data class GitRepoModel(
    @PrimaryKey
    val databaseId: String ,
    val id: String? = null,
//    val name: String? = null,
//    @SerializedName("full_name")
//    val fullName: String? = null,
//    @SerializedName("html_url")
//    val htmlUrl: String? = null,
    val avatarUrl: String? = null,
//    val description: String? = null,
//    @SerializedName("owner")
//    val owner: Owner? = null,
//    @SerializedName("created_at")
//    val createdAt: String? = null,
//    @SerializedName("updated_at")
//    val updatedAt: String? = null,
//    @SerializedName("forks_url")
//    val forks: List<RepoFork>? = null,
//    @SerializedName("starred_url")
//    val stars: List<RepoFork>? = null,
//    val language: String? = null
): Parcelable

