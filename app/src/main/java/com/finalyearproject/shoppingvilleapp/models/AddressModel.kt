package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AddressModel(
    var address_id: String = "",
    val name: String = "",
    val mobile: String = "",
    val province: String = "",
    val city: String = "",
    val label: String = "",
    val town: String = "",
    val address: String = ""
):Parcelable