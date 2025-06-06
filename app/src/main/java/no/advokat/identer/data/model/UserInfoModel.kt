package no.advokat.identer.data.model

data class UserInfoModel(
    val sub: String,
    val sid: String,
    val name: String,
    val given_name: String,
    val family_name: String,
    val phone_number: String,
    val address: Address,
    val other_addresses: List<Address> = emptyList(),
)

data class Address(
    val address_type: String?,
    val country: String?,
    val formatted: String?,
    val postal_code: String?,
    val region: String?,
    val street_address: String?
)