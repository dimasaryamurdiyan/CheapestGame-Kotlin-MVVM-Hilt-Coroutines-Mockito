package com.ewide.test.dimasaryamurdiyan.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.local.room.dao.GameDao

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

}