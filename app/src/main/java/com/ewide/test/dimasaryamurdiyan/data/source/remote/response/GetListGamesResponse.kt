package com.ewide.test.dimasaryamurdiyan.data.source.remote.response
import android.os.Parcelable


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class GetListGamesResponse : ArrayList<GetListGamesResponse.GetListGamesResponseItem>(){
    @Parcelize
    data class GetListGamesResponseItem(
        @SerializedName("gameID")
        val gameID: String? = null,
        @SerializedName("steamAppID")
        val steamAppID: String? = null,
        @SerializedName("cheapest")
        val cheapest: String? = null,
        @SerializedName("cheapestDealID")
        val cheapestDealID: String? = null,
        @SerializedName("external")
        val `external`: String? = null,
        @SerializedName("internalName")
        val internalName: String? = null,
        @SerializedName("thumb")
        val thumb: String? = null
    ) : Parcelable
}