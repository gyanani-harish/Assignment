package harish.mvvmexample.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import harish.mvvmexample.base.MyApplication
import harish.mvvmexample.di.module.ActivityBindingModule
import harish.mvvmexample.di.module.ApplicationModule
import harish.mvvmexample.di.module.ContextModule

import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class,
    ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<MyApplication> {

    override fun inject(application: MyApplication)

   @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): ApplicationComponent
    }
}
