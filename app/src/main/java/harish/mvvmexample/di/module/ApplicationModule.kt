package harish.mvvmexample.di.module

import dagger.Module
import dagger.Provides
import harish.mvvmexample.data.rest.RepoService
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter


@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    private val BASE_URL = "https://github-trending-api.now.sh/"

    @Singleton
    @Provides
    internal fun provideRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
        callAdapter: CallAdapter.Factory
    ): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(jsonConverter)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    internal fun provideRxJava2CallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    internal fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    internal fun provideRetrofitService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }
}
