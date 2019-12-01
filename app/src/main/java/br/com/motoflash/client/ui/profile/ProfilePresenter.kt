package br.com.motoflash.client.ui.profile

import br.com.motoflash.client.ui.base.BasePresenter
import br.com.motoflash.core.data.network.model.User
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.util.*
import javax.inject.Inject


class ProfilePresenter<V :ProfileMvpView> @Inject
constructor() : BasePresenter<V>(), ProfileMvpPresenter<V> {
    override fun doSingOutUser() {
        auth.signOut()
        mvpView?.run {
            onSingOutUser()
        }
    }
}