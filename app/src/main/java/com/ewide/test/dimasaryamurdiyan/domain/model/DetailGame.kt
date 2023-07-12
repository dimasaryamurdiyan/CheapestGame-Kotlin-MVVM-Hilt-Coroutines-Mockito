package com.ewide.test.dimasaryamurdiyan.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailGame(
    val info: Info? = null,
    val cheapestPriceEver: CheapestPriceEver? = null,
    val deals: List<Deal?>? = null
) : Parcelable {
    @Parcelize
    data class Info(
        val title: String? = null,
        val steamAppID: String? = null,
        val thumb: String? = null
    ) : Parcelable

    @Parcelize
    data class CheapestPriceEver(
        val price: String? = null,
        val date: Int? = null
    ) : Parcelable

    @Parcelize
    data class Deal(
        val storeID: String? = null,
        val dealID: String? = null,
        val price: String? = null,
        val retailPrice: String? = null,
        val savings: String? = null
    ) : Parcelable
}