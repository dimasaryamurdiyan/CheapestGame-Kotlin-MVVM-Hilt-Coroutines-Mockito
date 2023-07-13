package com.ewide.test.dimasaryamurdiyan.data.source.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.ewide.test.dimasaryamurdiyan.di.PreferenceInfo
import com.ewide.test.dimasaryamurdiyan.utils.Sort
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preference @Inject constructor(
    @ApplicationContext context: Context,
    @PreferenceInfo private val prefFileName: String
): IPreference {
    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setSortType(type: String) {
        mPrefs.edit { putString("sort", type) }
    }

    override fun getSortType(): String? {
        return mPrefs.getString("sort", Sort.DEFAULT.toString())
    }
}