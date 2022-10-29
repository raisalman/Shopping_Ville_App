package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val category_id:String="",
    val category_image: Int=0,
    val Category_name:String=""):Parcelable