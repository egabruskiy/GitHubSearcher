package com.egabruskiy.githubsearcher.di


import com.egabruskiy.githubsearcher.data.database.GitRepoDao
import com.egabruskiy.githubsearcher.data.repository.GitRepoRepository
import com.egabruskiy.githubsearcher.network.GitRepoRemote
import org.koin.dsl.module

val repositoryModule = module {

    single { provideGitRepoRepository(get(), get()) }
}

private fun provideGitRepoRepository(
    remote: GitRepoRemote,
    dao: GitRepoDao
): GitRepoRepository {
    return GitRepoRepository(remote,dao)
}
