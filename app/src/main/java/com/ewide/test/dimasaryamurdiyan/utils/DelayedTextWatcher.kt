package com.ewide.test.dimasaryamurdiyan.utils

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.*

class DelayedTextWatcher(private val delayMillis: Long, private val onTextChanged: (String) -> Unit) :
    TextWatcher {
    private var searchJob: Job? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Cancel any existing search job
        searchJob?.cancel()

        // Create a new search job with the specified delay
        searchJob = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMillis)
            onTextChanged(s.toString())
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }
}