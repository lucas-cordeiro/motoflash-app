package br.com.motoflash.client.ui.main

import br.com.motoflash.client.ui.base.BasePresenter
import br.com.motoflash.core.data.network.model.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.util.*
import javax.inject.Inject


class MainPresenter<V :MainMvpView> @Inject
constructor() : BasePresenter<V>(), MainMvpPresenter<V> {
    override fun doGetUser() {
        val user = auth.currentUser!!
        firestore
            .collection("users")
            .document(user.uid)
            .addSnapshotListener{ doc, e ->
                if(e != null){
                    log("Error: ${e.message}")
                    logoutUser()
                }else{
                    if(doc != null && doc.exists()){
                        val userDoc = doc.toObject(User::class.java)!!
                        if(userDoc.active == true){
                            mvpView?.onGetUser(userDoc)
                        }
                        else{
                            logoutUser()
                        }
                    }else{
                        logoutUser()
                    }
                }
            }
    }

    private fun logoutUser(){
        mvpView?.run {
            onLogoutUser()
        }
    }
}