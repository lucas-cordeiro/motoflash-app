package br.com.motoflash.client

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import br.com.motoflash.client.di.component.ApplicationComponent
import br.com.motoflash.client.di.component.DaggerApplicationComponent
import br.com.motoflash.client.di.module.ApplicationModule
import com.google.firebase.FirebaseApp

class BaseApp: Application() {


    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        FirebaseApp.initializeApp(this)

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }
}
