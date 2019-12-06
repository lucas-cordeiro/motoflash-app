package br.com.motoflash.courier.ui.main

import br.com.motoflash.core.data.network.model.Courier
import br.com.motoflash.core.ui.util.DEVICE_ID
import br.com.motoflash.courier.ui.base.BasePresenter
import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject

class MainPresenter<V :MainMvpView> @Inject
constructor() : BasePresenter<V>(), MainMvpPresenter<V> {
    override fun doGetCourier() {
        val user = auth.currentUser!!
        firestore
            .collection("couriers")
            .document(user.uid)
            .addSnapshotListener{ doc, e ->
                if(e != null){
                    log("Error: ${e.message}")
                    logoutUser()
                }else{
                    if(doc != null && doc.exists()){
                        val userDoc = doc.toObject(Courier::class.java)!!
                        userDoc.id = doc.id

                        val device = getUserDevice(Prefs.getString(DEVICE_ID, ""))

                        if(userDoc.active){
                            firestore
                                .collection("users")
                                .document(user.uid)
                                .collection("devices")
                                .document(device.uniqueId!!)
                                .set(device)
                                .addOnCompleteListener {
                                    mvpView?.onGetCourier(userDoc)
                                }
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
            onLogoutCourier()
        }
    }
}