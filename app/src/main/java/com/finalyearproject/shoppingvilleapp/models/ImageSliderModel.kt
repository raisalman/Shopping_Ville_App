package com.finalyearproject.shoppingvilleapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ImageSliderModel(
    var description: String = "",
    var imageRes: Int =1
    ):Parcelable