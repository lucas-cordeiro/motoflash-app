package br.com.motoflash.core.data.network.model

import com.google.firebase.firestore.model.value.TimestampValue


data class Courier(
    var id: String? = null,
    var name: String? = null,
    var cnh: Boolean = false,
    var cnhDoc: String? = null,
    var cnhNumber: String? = null,
    var createdDate: TimestampValue? = null,
    var currentEquipment: Equipment? = null,
    var email: String? = null,
    var location: Location? = null,
    var mobilePhone: String? = null,
    var online: Boolean = false,
    var password: String? = null,
    var running: Boolean = false
)