package harish.mvvmexample.ui.main

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.ui.list.ListFragment
import harish.mvvmexample.util.ViewModelFactory

interface NavigationListener {
    fun goToDetailsFragment(
        sharedView: ImageView,
        transitionName: String,
        repo: TrendingRepo,
        viewModelFactory: ViewModelFactory,
        fragmentManager: FragmentManager?,
        listFragment: ListFragment
    )
}