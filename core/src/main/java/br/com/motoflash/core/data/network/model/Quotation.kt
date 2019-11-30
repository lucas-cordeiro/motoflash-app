package br.com.motoflash.core.data.network.model

data class Quotation(
    var id: String? = null,
    var companyId: String? = null,
    var createdDate: Long? = null,
    var distance: Long? = null,
    var duration: Long? = null,
    var price: Double? = null
)