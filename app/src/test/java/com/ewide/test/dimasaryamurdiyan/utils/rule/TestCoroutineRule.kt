package com.ewide.test.dimasaryamurdiyan.utils.rule

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {
    private val coroutineDispatcher = TestCoroutineDispatcher()
    private val coroutineScope = TestCoroutineScope(coroutineDispatcher)
    override fun apply(base: Statement, description: Description): Statement = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(coroutineDispatcher)
            base.evaluate()
            Dispatchers.resetMain()
            coroutineScope.cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        coroutineScope.runBlockingTest { block() }

}