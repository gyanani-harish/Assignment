package harish.mvvmexample.ui.detail.view

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import com.bumptech.glide.Glide
import com.example.basemodule.utils.AlertUtils
import harish.mvvmexample.R
import harish.mvvmexample.base.MyBaseFragment
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.ui.detail.viewmodel.DetailsViewModel
import harish.mvvmexample.ui.main.listeners.ActionBarVisibilityListener
import harish.mvvmexample.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.*

import javax.inject.Inject


class DetailsFragment @Inject constructor() : MyBaseFragment() {
    private var transitionName: String? = null
    override val layoutRes: Int
        get() = R.layout.fragment_details

    @BindView(R.id.img)
    internal lateinit var img: ImageView
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var selectedRepo: TrendingRepo

    companion object{
        const val INTENT_KEY_TRANSITION_NAME="transitionName"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        detailsViewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(DetailsViewModel::class.java)
        selectedRepo = detailsViewModel.selectedRepo.value!!
        setData(selectedRepo)
    }

    private fun setData(selectedRepo: TrendingRepo) {
        setImage(transitionName, selectedRepo.avatar)
        tv_name.text = selectedRepo.name
        tv_username.text = selectedRepo.username
        tv_url.text = selectedRepo.url
        tv_repo_name.text = selectedRepo.repo.name
        tv_repo_url.text = selectedRepo.repo.url
        tv_repo_description.text = selectedRepo.repo.description
    }

    private fun setImage(transitionName: String?, imgURL: String?) {
        if (transitionName != null) {
            img.transitionName = transitionName
        } else {
            AlertUtils.longToast(context, getString(R.string.error_showing_shared_transition))
        }
        if (!TextUtils.isEmpty(imgURL)) {

            Glide
                .with(context!!)
                .load(imgURL)
                .placeholder(R.mipmap.ic_launcher)
                .into(img)

        } else {
            AlertUtils.longToast(context, getString(R.string.error_showing_details_img))
        }
    }

    private fun getDataFromBundle(bundle: Bundle?) {
        if (bundle != null) {
            transitionName = bundle.getString(INTENT_KEY_TRANSITION_NAME)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val listener = activity as ActionBarVisibilityListener
        listener.showActionBarBackButton()
        getDataFromBundle(arguments)
    }
}
