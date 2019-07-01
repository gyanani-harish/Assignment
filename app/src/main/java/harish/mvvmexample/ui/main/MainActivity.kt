package harish.mvvmexample.ui.main

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import harish.mvvmexample.R
import harish.mvvmexample.base.MyBaseActivity
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.ui.detail.DetailsFragment
import harish.mvvmexample.ui.detail.DetailsViewModel
import harish.mvvmexample.ui.list.ListFragment
import harish.mvvmexample.ui.list.SearchListener
import harish.mvvmexample.ui.list.TransitionListener
import harish.mvvmexample.util.ViewModelFactory
import android.view.MenuItem




class MainActivity : MyBaseActivity(), NavigationListener, ActionBarVisibilityListener {


    override val layoutRes: Int
        get() = R.layout.activity_main
    private val listenersList = ArrayList<SearchListener>()
    private lateinit var transitionListener: TransitionListener
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        val toolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            listenersList.add(listFragment as SearchListener)
            transitionListener = listFragment
            addFragment(listFragment, ListFragment::class.java.simpleName)
        }
    }

    override fun showActionBarBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun hideActionBarBackButton() {
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
        else {
           val backStackEntry =  supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount-1)
            val tag = backStackEntry.getName()
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            if(fragment is RefreshListener){
                fragment.onRefresh(null)
            }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}