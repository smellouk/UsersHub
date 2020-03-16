package io.mellouk.common_android

import io.mellouk.common_android.base.BaseCommand

interface Commandable<Cmd : BaseCommand> {
    fun onCommand(cmd: Cmd)
}