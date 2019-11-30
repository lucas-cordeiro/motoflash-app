package br.com.motoflash.core.data.network.model

import com.google.firebase.firestore.model.value.TimestampValue

data class Equipment(
    var brand: String? = null,
    var createdDate: TimestampValue? = null,
    var model: String? = null,
    var plate: String? = null,
    var year: Long? = null
)