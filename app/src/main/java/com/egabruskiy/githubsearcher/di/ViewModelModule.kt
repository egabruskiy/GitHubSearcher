package com.egabruskiy.githubsearcher.di


import com.egabruskiy.githubsearcher.data.repository.GitRepoRepository
import com.egabruskiy.githubsearcher.ui.detailRepoFragment.DetailRepoViewModel
import com.egabruskiy.githubsearcher.ui.savelist.SaveListViewModel
import com.egabruskiy.githubsearcher.ui.searcfragment.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { provideSearchViewModel(get()) }
    viewModel { provideDetailFragmentViewModel(get()) }
    viewModel { provideSaveListViewModel(get()) }
}

private fun provideSearchViewModel(repository: GitRepoRepository): SearchViewModel {
    return SearchViewModel(repository)
}
private fun provideDetailFragmentViewModel(repository: GitRepoRepository): DetailRepoViewModel {
    return DetailRepoViewModel(repository)
}
private fun provideSaveListViewModel(repository: GitRepoRepository): SaveListViewModel {
    return SaveListViewModel(repository)
}