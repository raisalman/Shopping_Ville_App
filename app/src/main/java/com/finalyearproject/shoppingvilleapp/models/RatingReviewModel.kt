package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RatingReviewModel(
    var name:String="",
    var image:Int=0,
    var rating:Float=0.0f,
    var review:String=""
):Parcelable