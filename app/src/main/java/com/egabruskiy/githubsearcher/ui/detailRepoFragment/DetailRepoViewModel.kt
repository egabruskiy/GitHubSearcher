package com.egabruskiy.githubsearcher.ui.detailRepoFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.data.repository.GitRepoRepository
import kotlinx.coroutines.launch

class DetailRepoViewModel(private val repository: GitRepoRepository) : ViewModel() {



    fun saveRepo(gitRepoModel: GitRepoModel){
        viewModelScope.launch {
            repository.saveRepository(gitRepoModel)
        }
    }
    fun deleteRepo(gitRepoModel: GitRepoModel){
        repository.removeRepository(gitRepoModel)
    }

    val savedRepositories: LiveData<ResourceResult<List<GitRepoModel>>> = repository.savedRepositories.asLiveData()

    fun getRepo(id:String) : LiveData<ResourceResult<GitRepoModel>> =  repository.repoById(id).asLiveData()


}