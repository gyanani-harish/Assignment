package harish.mvvmexample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.data.rest.RepoRepository
import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

class DetailsViewModel @Inject
constructor(private val repoRepository: RepoRepository) : ViewModel() {
    private val BUNDLE_KEY_REPO_DETAILS = "repo_details"
    private var disposable: CompositeDisposable? = null

    val selectedRepo = MutableLiveData<TrendingRepo>()

    fun getSelectedRepo(): LiveData<TrendingRepo> {
        return selectedRepo
    }

    init {
        disposable = CompositeDisposable()
    }

    fun setSelectedRepo(repo: TrendingRepo) {
        selectedRepo.value = repo
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}
