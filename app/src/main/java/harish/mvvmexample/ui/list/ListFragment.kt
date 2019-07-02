package harish.mvvmexample.ui.list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import harish.mvvmexample.base.MyBaseFragment
import harish.mvvmexample.data.model.TrendingRepo

import javax.inject.Inject
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.example.basemodule.utils.AlertUtils
import com.example.basemodule.utils.KeyboardUtils
import harish.mvvmexample.R
import harish.mvvmexample.ui.main.ActionBarVisibilityListener
import harish.mvvmexample.ui.main.NavigationListener
import harish.mvvmexample.ui.main.RefreshListener
import harish.mvvmexample.util.ViewModelFactory
import kotlinx.android.synthetic.main.screen_list.*


class ListFragment : MyBaseFragment(), RepoSelectedListener, SearchListener,
    TransitionListener,RefreshListener {
    private var selectedLanguage: String = ""
    private var selectedTimePeriod: String = ""
    @BindView(R.id.tv_error)
    internal lateinit var errorTextView: TextView
    @BindView(R.id.loading_view)
    internal lateinit var loadingView: View

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private var viewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val listener = activity as ActionBarVisibilityListener
        listener.hideActionBarBackButton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        recyclerView.addItemDecoration(DividerItemDecoration(baseActivity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = RepoListAdapter(viewModel!!, this, this)
        recyclerView.layoutManager = LinearLayoutManager(context)

        observableViewModel()
    }

    override fun onRepoSelected(repo: TrendingRepo, sharedView: ImageView) {
        val nav = context as NavigationListener
        nav.goToDetailsFragment(sharedView, sharedView.transitionName,
            repo, viewModelFactory, fragmentManager, this@ListFragment)
    }

    private fun observableViewModel() {

        viewModel!!.getRepos().observe(
            this,
            Observer<List<TrendingRepo>> { repos -> if (repos != null) recyclerView.visibility = View.VISIBLE })

        viewModel!!.error.observe(this, Observer<Int> { error ->
            if (error == 0) {
                errorTextView.visibility = View.GONE
                errorTextView.text = null
            } else {
                errorTextView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                errorTextView.text = getString(error)
            }
        })

        viewModel!!.loading.observe(this, Observer<Boolean> { isLoading ->
            if (isLoading != null) {
                loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    errorTextView.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }


    override fun performSearch(language: String, timePeriod: String) {
        viewModel!!.fetchRepos(language, timePeriod)
    }

    override val layoutRes: Int
        get() = R.layout.screen_list

    private lateinit var searchView: SearchView

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, menuInflater)
        if (context == null) {
            return
        }
        menuInflater!!.inflate(R.menu.menu_main, menu)
        val search: MenuItem = menu!!.findItem(R.id.action_search)
        searchView = search.actionView as SearchView

        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.setQueryHint("Enter language");
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearchIconClicked(query)
                return true
            }

            override
            fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
        if(!TextUtils.isEmpty(selectedTimePeriod) && !TextUtils.isEmpty(selectedLanguage)){
            //no need to call initial api
            return
        }
        selectedTimePeriod = getString(R.string.weekly)
        searchView.setQuery("java", true)
        AlertUtils.longToast(context!!,getString(R.string.initial_filter_msg))
    }

    private fun onSearchIconClicked(query: String) {
        if (TextUtils.isEmpty(query)) {
            AlertUtils.shortToast(context, getString(R.string.validation_type_something_to_search))
            return
        }
        KeyboardUtils.closeKeyBoard(context)
        selectedLanguage = query
        performSearch(query, selectedTimePeriod)

    }

    override fun setTransitions() {
        this.sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        this.exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)
    }

    override fun onRefresh(payload: Any?) {
        val listener = activity as ActionBarVisibilityListener
        listener.hideActionBarBackButton()
    }
}
