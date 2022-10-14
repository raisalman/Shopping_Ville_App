package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CardModel(
    val card_id:String="",
    val card_holder_name:String="",
    val card_number:String="",
    val expiry_date:String="",
    val security_code:String="",
    val check_box:Boolean=false
):Parcelable