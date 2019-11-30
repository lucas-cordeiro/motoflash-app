package br.com.motoflash.client.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import br.com.motoflash.client.di.annotations.ActivityContext
import br.com.motoflash.client.ui.splash.SplashMvpPresenter
import br.com.motoflash.client.ui.splash.SplashMvpView
import br.com.motoflash.client.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    internal fun providSplashMvpPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> =
        presenter

}