package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CartModel(
    val id:String="",
    val image: Int=0,
    val name: String="",
    val price:Float=0.0f,
    val quantity:Int=0
    ): Parcelable