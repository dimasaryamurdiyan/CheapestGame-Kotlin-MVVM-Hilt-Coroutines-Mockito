package com.ewide.test.dimasaryamurdiyan.utils

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

class DelayedTextWatcher(private val delayMillis: Long, private val onTextChanged: (String) -> Unit) :
    TextWatcher {
    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Not used in this example
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Cancel any pending execution of the runnable
        runnable?.let { handler.removeCallbacks(it) }

        // Create a new runnable with the specified delay
        runnable = Runnable { onTextChanged(s.toString()) }
        handler.postDelayed(runnable!!, delayMillis)
    }

    override fun afterTextChanged(s: Editable?) {
        // Not used in this example
    }
}