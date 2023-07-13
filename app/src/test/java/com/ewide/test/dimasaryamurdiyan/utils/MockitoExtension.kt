package com.ewide.test.dimasaryamurdiyan.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int, delayMillis: Long = 0L) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    val mockResponse = source?.let {
        MockResponse()
            .setResponseCode(code)
            .setBody(it.readString(StandardCharsets.UTF_8))
    }

    if (delayMillis > 0L) {
        mockResponse?.setBodyDelay(delayMillis, TimeUnit.MILLISECONDS)
    }

    mockResponse?.let { enqueue(it) }

}