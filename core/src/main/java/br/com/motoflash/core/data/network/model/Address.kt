package br.com.motoflash.core.data.network.model

data class Address(
    var address1: String? = null,
    var address2: String? = null,
    var city: String? = null,
    var location: Location? = null,
    var neighborhood: String? = null,
    var number: String? = null,
    var state: String? = null
)