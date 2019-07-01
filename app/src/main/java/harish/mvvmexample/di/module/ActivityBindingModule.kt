package harish.mvvmexample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import harish.mvvmexample.ui.main.MainActivity
import harish.mvvmexample.ui.main.MainFragmentBindingModule

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}
