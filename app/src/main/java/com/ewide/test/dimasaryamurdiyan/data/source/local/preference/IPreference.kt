package com.ewide.test.dimasaryamurdiyan.data.source.local.preference

import com.ewide.test.dimasaryamurdiyan.utils.Sort

interface IPreference {
    fun setSortType(type: String)

    fun getSortType(): String?
}