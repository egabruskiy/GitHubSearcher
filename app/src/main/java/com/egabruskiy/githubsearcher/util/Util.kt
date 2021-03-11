package com.egabruskiy.githubsearcher.util

import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import java.util.*

class Util {


    companion object{

        fun getRepoModelFromResponse(repositoryResponse: RepositoryResponse): GitRepoModel{

            return GitRepoModel(databaseId = "${UUID.randomUUID()}",
                id = repositoryResponse.id,
                avatarUrl = repositoryResponse.owner?.avatarUrl)


        }

    }
}