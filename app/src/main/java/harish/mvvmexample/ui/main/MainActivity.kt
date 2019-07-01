package harish.mvvmexample.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.basemodule.utils.AlertUtils
import com.example.basemodule.utils.KeyboardUtils
import com.example.basemodule.utils.StringUtils
import harish.mvvmexample.R
import harish.mvvmexample.base.MyBaseActivity
import harish.mvvmexample.ui.list.ListFragment
import harish.mvvmexample.ui.list.SearchListener
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.ui.detail.DetailsFragment
import harish.mvvmexample.ui.detail.DetailsViewModel
import harish.mvvmexample.ui.list.TransitionListener
import harish.mvvmexample.util.ViewModelFactory


class MainActivity : MyBaseActivity(), NavigationListener {


    override val layoutRes: Int
        get() = R.layout.activity_main
    private val listenersList = ArrayList<SearchListener>()
    private lateinit var transitionListener: TransitionListener
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            listenersList.add(listFragment as SearchListener)
            transitionListener = listFragment
            addFragment(listFragment, ListFragment::class.java.simpleName)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    override fun goToDetailsFragment(
        sharedView: View,
        transitionName: String,
        repo: TrendingRepo,
        viewModelFactory: ViewModelFactory,
        fragmentManager: FragmentManager?
    ) {
        transitionListener.setTransitions()
        val detailsFragment = DetailsFragment()
        detailsFragment.sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
        detailsFragment.enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
        val bundle = Bundle()
        bundle.putString("transitionName", sharedView.transitionName)
        detailsFragment.arguments = bundle

        val fragmentTransaction = fragmentManager?.beginTransaction()
        val detailsTag = DetailsFragment::class.java.simpleName
        fragmentTransaction?.add(R.id.screenContainer, detailsFragment, detailsTag)
        fragmentTransaction?.addToBackStack(detailsTag)
        fragmentTransaction?.addSharedElement(sharedView, sharedView.transitionName)
        //detailsFragment.sharedElementReturnTransition = DetailsTransformation()
        fragmentTransaction?.commit()

        val detailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        detailsViewModel.selectedRepo.value = repo
    }
}