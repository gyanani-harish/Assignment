package harish.mvvmexample.ui.main.binding_module


import dagger.Module
import dagger.android.ContributesAndroidInjector
import harish.mvvmexample.ui.detail.view.DetailsFragment
import harish.mvvmexample.ui.list.view.ListFragment

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun provideDetailsFragment(): DetailsFragment
}