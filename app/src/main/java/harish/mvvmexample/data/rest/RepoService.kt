package harish.mvvmexample.data.rest

import harish.mvvmexample.data.model.TrendingRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoService {


    @GET("developers")
    fun getTrendingRepos(@Query("language") language: String, @Query("since") since: String): Single<List<TrendingRepo>>

}
