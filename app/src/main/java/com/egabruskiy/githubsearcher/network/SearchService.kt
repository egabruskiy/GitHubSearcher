package com.egabruskiy.githubsearcher.network

import com.egabruskiy.githubsearcher.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SearchService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String
//        @Path("q") query: String
//        @Query("page") page: Int,
//        @Query("per_page") itemsPerPage: Int
    ): SearchResponse
}