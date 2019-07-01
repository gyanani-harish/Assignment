package harish.mvvmexample.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.example.basemodule.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import harish.mvvmexample.R
import kotlinx.android.synthetic.main.activity_main.view.*

import javax.inject.Inject

abstract class MyBaseActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatcher: DispatchingAndroidInjector<Fragment>

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        ButterKnife.bind(this)

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatcher
    }
    protected fun addFragment(
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.screenContainer, fragment, fragmentTag)
            .addToBackStack(fragmentTag)
            .commit()
    }
}
