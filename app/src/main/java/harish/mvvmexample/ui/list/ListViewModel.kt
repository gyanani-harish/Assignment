package harish.mvvmexample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemodule.utils.KeyboardUtils
import harish.mvvmexample.R
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.data.rest.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class ListViewModel @Inject
constructor(internal var repoRepository: RepoRepository) : ViewModel() {
    private var dispo: DisposableSingleObserver<List<TrendingRepo>>? = null
    private var disposable: CompositeDisposable? = null
    internal var repos = MutableLiveData<List<TrendingRepo>>()
    private val repoLoadError = MutableLiveData<Int>()
    val loading = MutableLiveData<Boolean>()
    internal val error: LiveData<Int>
        get() = repoLoadError

    init {
        disposable = CompositeDisposable()
        //fetchRepos("java", "weekly")
    }

    fun fetchRepos(language: String, timePeriod: String) {
        loading.value = true
        if (dispo != null) {
            disposable!!.remove(dispo!!)
        }

        dispo = repoRepository.getTrendingRepos(language, timePeriod).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                DisposableSingleObserver<List<TrendingRepo>>() {
                override fun onSuccess(value: List<TrendingRepo>) {
                    if (value.isEmpty()) {
                        repoLoadError.value = R.string.no_results_found
                    } else {
                        repoLoadError.value = 0
                    }
                    repos.value = value
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    repoLoadError.value = R.string.unable_to_get_valid_server_response
                    loading.value = false
                }
            })

        disposable!!.add(dispo as DisposableSingleObserver<List<TrendingRepo>>)
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }

    fun getRepos(): LiveData<List<TrendingRepo>> {
        return repos
    }


}
