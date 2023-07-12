package com.ewide.test.dimasaryamurdiyan.data.source.remote.response
import android.os.Parcelable

import kotlinx.parcelize.Parcelize

import com.google.gson.annotations.SerializedName


@Parcelize
data class GetDetailGameResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("cheapestPriceEver")
    val cheapestPriceEver: CheapestPriceEver? = null,
    @SerializedName("deals")
    val deals: List<Deal?>? = null
) : Parcelable {
    @Parcelize
    data class Info(
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("steamAppID")
        val steamAppID: String? = null,
        @SerializedName("thumb")
        val thumb: String? = null
    ) : Parcelable

    @Parcelize
    data class CheapestPriceEver(
        @SerializedName("price")
        val price: String? = null,
        @SerializedName("date")
        val date: Int? = null
    ) : Parcelable

    @Parcelize
    data class Deal(
        @SerializedName("storeID")
        val storeID: String? = null,
        @SerializedName("dealID")
        val dealID: String? = null,
        @SerializedName("price")
        val price: String? = null,
        @SerializedName("retailPrice")
        val retailPrice: String? = null,
        @SerializedName("savings")
        val savings: String? = null
    ) : Parcelable
}