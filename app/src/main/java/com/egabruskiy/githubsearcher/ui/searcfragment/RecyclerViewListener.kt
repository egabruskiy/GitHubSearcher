package com.egabruskiy.githubsearcher.ui.searcfragment

import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse


interface RecyclerViewListener {
    fun onClick( response: RepositoryResponse)
}