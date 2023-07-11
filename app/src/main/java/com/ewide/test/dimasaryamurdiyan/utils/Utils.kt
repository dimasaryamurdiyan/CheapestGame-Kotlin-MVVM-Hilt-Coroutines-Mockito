package com.ewide.test.dimasaryamurdiyan.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import com.ewide.test.dimasaryamurdiyan.R

object Utils {

    fun showLoadingDialog(context: Context?): ProgressDialog? {
        context?.let {
            val progressDialog = ProgressDialog(context)
            progressDialog.let {
                it.show()
                it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
                it.setContentView(R.layout.layout_progress_dialog)
                it.isIndeterminate = true
                it.setCancelable(false)
                it.setCanceledOnTouchOutside(false)
                return it
            }
        }
        return null
    }
}