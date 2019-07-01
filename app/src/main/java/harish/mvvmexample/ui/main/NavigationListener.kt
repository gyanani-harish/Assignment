package harish.mvvmexample.ui.main

import android.view.View
import androidx.fragment.app.FragmentManager
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.util.ViewModelFactory

interface NavigationListener {
    fun goToDetailsFragment(
        sharedView: View,
        transitionName: String,
        repo: TrendingRepo,
        viewModelFactory: ViewModelFactory,
        fragmentManager: FragmentManager?
    )
}