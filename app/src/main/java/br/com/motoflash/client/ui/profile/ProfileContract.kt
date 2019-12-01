package br.com.motoflash.client.ui.profile

import br.com.motoflash.client.ui.base.MvpPresenter
import br.com.motoflash.client.ui.base.MvpView
import br.com.motoflash.core.data.network.model.User
import com.google.firebase.auth.FirebaseUser

interface ProfileMvpPresenter<V :ProfileMvpView> : MvpPresenter<V> {
    fun doSingOutUser()
}

interface ProfileMvpView : MvpView {
    fun onSingOutUser()
    fun onSingOutFail()
}


