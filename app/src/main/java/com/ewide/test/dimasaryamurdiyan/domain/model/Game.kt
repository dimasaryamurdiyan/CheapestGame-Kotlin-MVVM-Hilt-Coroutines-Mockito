package com.ewide.test.dimasaryamurdiyan.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val gameID: String? = null,
    val steamAppID: String? = null,
    val cheapest: String? = null,
    val cheapestDealID: String? = null,
    val external: String? = null,
    val internalName: String? = null,
    val thumb: String? = null,
    val isFavorite: Boolean
) : Parcelable