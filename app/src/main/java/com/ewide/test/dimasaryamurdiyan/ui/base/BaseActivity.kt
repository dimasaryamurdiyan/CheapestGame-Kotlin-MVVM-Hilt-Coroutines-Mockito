package com.ewide.test.dimasaryamurdiyan.ui.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.dimasaryamurdiyan.utils.Utils

abstract class BaseActivity : AppCompatActivity()  {
    protected var progressDialog: ProgressDialog? = null

    fun hideLoading() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        progressDialog = Utils.showLoadingDialog(this)
    }
}