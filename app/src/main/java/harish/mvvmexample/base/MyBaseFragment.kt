package harish.mvvmexample.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.basemodule.base.BaseFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

abstract class MyBaseFragment : BaseFragment() {
    private var unbinder: Unbinder? = null
    var baseActivity: AppCompatActivity? = null
        private set
    @Inject
    internal lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        baseActivity = context as AppCompatActivity?
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder!!.unbind()
            unbinder = null
        }
    }
}
