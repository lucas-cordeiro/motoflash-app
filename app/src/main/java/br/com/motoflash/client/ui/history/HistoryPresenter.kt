package br.com.motoflash.client.ui.history

import br.com.motoflash.client.ui.base.BasePresenter
import br.com.motoflash.core.data.network.model.WorkOrder
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class HistoryPresenter<V :HistoryMvpView> @Inject
constructor() : BasePresenter<V>(), HistoryMvpPresenter<V> {
    private var removeListenerRegistration: ListenerRegistration? = null
    override fun doGetWorkOrders(userId: String) {
    removeListenerRegistration = firestore.collection("workorders").orderBy("createdDate", Query.Direction.DESCENDING).whereEqualTo("userId",userId).addSnapshotListener { querySnapshot, e ->
            if(e==null){
                log("success")
                mvpView?.onGetWorkOrders(querySnapshot!!.documents.map { it.toObject(WorkOrder::class.java)!!.apply {
                    id = it.id
                } })
            }else{
                log("Error: ${e.message}")
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        removeListenerRegistration?.remove()
    }
}