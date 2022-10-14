package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserModel(
    var user_id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email_id: String = "",
    var image: String = "",
    var mobile: String="",
    var gender: String =""):Parcelable