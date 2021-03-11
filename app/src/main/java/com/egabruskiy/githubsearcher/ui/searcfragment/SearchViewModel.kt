package com.egabruskiy.githubsearcher.ui.searcfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.data.repository.GitRepoRepository
import com.egabruskiy.githubsearcher.util.DEBOUNCE_MILLIS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class SearchViewModel (private val repository: GitRepoRepository): ViewModel() {


    private val query = MutableStateFlow("")
    fun setQuery(text: String) {
        query.value = text
    }
    val images: LiveData<ResourceResult<List<RepositoryResponse>>> = query
        .debounce(DEBOUNCE_MILLIS)
        .filter { text ->
            text.isNotEmpty()
        }
        .flatMapLatest { text ->
            repository.getReposByQuery(text)
        }
        .asLiveData()



    fun saveRepository(repoModel: GitRepoModel){

    }
}