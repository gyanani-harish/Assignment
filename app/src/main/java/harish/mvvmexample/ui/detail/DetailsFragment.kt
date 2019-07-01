package harish.mvvmexample.ui.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.basemodule.utils.AlertUtils
import harish.mvvmexample.R
import harish.mvvmexample.base.MyBaseFragment
import harish.mvvmexample.data.model.TrendingRepo
import harish.mvvmexample.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.*

import javax.inject.Inject


class DetailsFragment : MyBaseFragment() {
    private var transitionName: String? = null
    override val layoutRes: Int
        get() = R.layout.fragment_details
    private var startingPosition: Int = 0
    private var currentPosition: Int = 0
    private var isReturning: Boolean = false
    @BindView(R.id.img)
    internal lateinit var img: ImageView
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var selectedRepo: TrendingRepo



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)
        detailsViewModel = ViewModelProviders.of(baseActivity!!, viewModelFactory).get(DetailsViewModel::class.java)
        selectedRepo = detailsViewModel.selectedRepo.value!!
        getDataFromBundle(arguments)
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
            context?.let {
                Glide

                    .with(it)
                    .load(imgURL)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img)
            }
        } else {
            AlertUtils.longToast(context, getString(R.string.error_showing_details_img))
        }
    }

    private fun getDataFromBundle(bundle: Bundle?) {
        if (bundle != null) {
            transitionName = bundle.getString("transitionName")
        }
    }

}
