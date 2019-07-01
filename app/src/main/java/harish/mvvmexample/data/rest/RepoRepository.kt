package harish.mvvmexample.data.rest

import harish.mvvmexample.data.model.TrendingRepo
import io.reactivex.Single
import javax.inject.Inject

class RepoRepository @Inject
constructor(private val repoService: RepoService) {



    fun getTrendingRepos(language: String, since: String): Single<List<TrendingRepo>> {
        return repoService.getTrendingRepos(language, since)
    }
}
