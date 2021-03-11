package com.egabruskiy.githubsearcher.ui.savelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.data.repository.GitRepoRepository

class SaveListViewModel(private val repository: GitRepoRepository) : ViewModel() {

    val savedRepositories: LiveData<ResourceResult<List<GitRepoModel>>> = repository.savedRepositories.asLiveData()
}