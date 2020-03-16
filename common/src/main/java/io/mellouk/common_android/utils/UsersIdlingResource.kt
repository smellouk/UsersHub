package io.mellouk.common_android.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import java.util.concurrent.atomic.AtomicBoolean

class UsersIdlingResource : IdlingResource {
    private val isIdle = AtomicBoolean(true)

    private var callback: ResourceCallback? = null

    override fun getName(): String = this.javaClass.name

    override fun isIdleNow(): Boolean = isIdle.get()

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        this.callback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        isIdle.set(isIdleNow)
        if (isIdleNow) {
            callback?.onTransitionToIdle()
        }
    }
}
