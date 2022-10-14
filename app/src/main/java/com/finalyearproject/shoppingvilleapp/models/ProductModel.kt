package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    val id:String="",
    val image: Int=0,
    val name: String="",
    val brand_name:String="",
    val price:Float=0.0f,
    val description:String="",
    val delivery_time:String="",
    val category:String="",
    val shipping_fee:Float=0.0f,
    val condition:String="",
    val rating:Float=0.0f,
    val reviews_count:Int=0,
):Parcelable
