package com.ewide.test.dimasaryamurdiyan.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "gameID")
    var gameID: String,

    @ColumnInfo(name = "steamAppID")
    var steamAppID: String? = null,

    @ColumnInfo(name = "cheapest")
    var cheapest: String? = null,

    @ColumnInfo(name = "cheapestDealID")
    var cheapestDealID: String? = null,

    @ColumnInfo(name = "external")
    var external: String? = null,

    @ColumnInfo(name = "internalName")
    var internalName: String? = null,

    @ColumnInfo(name = "thumb")
    var thumb: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)