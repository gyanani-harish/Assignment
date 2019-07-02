package harish.mvvmexample.ui.main.listeners

import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.ui.list.view.ListFragment
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