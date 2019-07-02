package harish.mvvmexample.ui.list.listeners

import android.widget.ImageView
import harish.mvvmexample.data.model.TrendingRepo

interface RepoSelectedListener {

    fun onRepoSelected(repo: TrendingRepo, sharedView: ImageView)
}
