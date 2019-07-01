package harish.mvvmexample.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import harish.mvvmexample.R
import harish.mvvmexample.data.model.TrendingRepo
import java.util.*


class RepoListAdapter internal constructor(
    viewModel: ListViewModel,
    lifecycleOwner: LifecycleOwner,
    private val repoSelectedListener: RepoSelectedListener
) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {
    private var lastPosition: Int = -1
    private val data = ArrayList<TrendingRepo>()

    init {
        viewModel.getRepos().observe(lifecycleOwner, Observer<List<TrendingRepo>> { repos ->
            data.clear()
            if (repos != null) {
                data.addAll(repos)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(harish.mvvmexample.R.layout.view_repo_list_item, parent, false)
        return RepoViewHolder(view, repoSelectedListener)
    }

    override fun onViewDetachedFromWindow(holder: RepoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
        val animation = AnimationUtils.loadAnimation(
            holder.imgUser.context,
            if (position > lastPosition)
                R.anim.up_from_bottom
            else
                R.anim.down_from_top
        )
        holder.itemView.startAnimation(animation)
        lastPosition = position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].url.hashCode().toLong()
    }

    inner class RepoViewHolder(itemView: View, repoSelectedListener: RepoSelectedListener) :
        RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_repo_name)
        lateinit var repoNameTextView: TextView
        @BindView(R.id.tv_name)
        lateinit var repoDescriptionTextView: TextView

        @BindView(R.id.img_user)
        lateinit var imgUser: ImageView

        private var repo: TrendingRepo? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v ->
                if (repo != null) {
                    imgUser.transitionName = "transition" + adapterPosition
                    repoSelectedListener.onRepoSelected(repo!!, imgUser)
                }
            }
        }

        fun bind(repo: TrendingRepo) {
            this.repo = repo
            repoNameTextView.text = repo.repo.name
            repoDescriptionTextView.text = repo.name

            Glide
                .with(imgUser.context)
                .load(repo.avatar)
                .apply(RequestOptions.circleCropTransform())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(imgUser)
        }
    }
}
