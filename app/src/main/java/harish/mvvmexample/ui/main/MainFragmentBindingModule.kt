package harish.mvvmexample.ui.main


import dagger.Module
import dagger.android.ContributesAndroidInjector
import harish.mvvmexample.ui.detail.DetailsFragment
import harish.mvvmexample.ui.list.ListFragment

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun provideDetailsFragment(): DetailsFragment
}