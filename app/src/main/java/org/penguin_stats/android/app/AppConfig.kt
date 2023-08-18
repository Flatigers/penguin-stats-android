package org.penguin_stats.android.app

import android.content.Context
import androidx.core.content.edit
import org.penguin_stats.android.R


private enum class Domain {
    CN,
    IO
}

private enum class Server {
    CN,
    US,
    JP,
    KR
}

object AppConfig {
    const val versionName = "0.1"

    fun isMirror() = Domain.CN == Domain.valueOf(
        sharedPreference()
            .getString("domain", "CN")!!
    )

    fun getDomain() = if (isMirror()) {
        "cn"
    } else {
        "io"
    }

    fun setDomainCN() {
        editPreference("domain", "CN")
    }

    fun setDomainIO() {
        editPreference("domain", "IO")
    }


    fun whichServer() = when (Server.valueOf(
        sharedPreference().getString("server", "CN")!!
    )) {
        Server.CN -> R.id.s_server_cn
        Server.US -> R.id.s_server_us
        Server.JP -> R.id.s_server_jp
        Server.KR -> R.id.s_server_kr
    }

    fun getServer() = when (Server.valueOf(
        sharedPreference().getString("server", "CN")!!
    )) {
        Server.CN -> "CN"
        Server.US -> "US"
        Server.JP -> "JP"
        Server.KR -> "KR"
    }

    fun setServerCN() = editPreference("server", "CN")
    fun setServerUS() = editPreference("server", "US")
    fun setServerJP() = editPreference("server", "JP")
    fun setServerKR() = editPreference("server", "KR")


    private fun sharedPreference() = BaseApplication.context
        .getSharedPreferences("settings", Context.MODE_PRIVATE)

    private fun editPreference(k: String, v: String) {
        sharedPreference()?.edit {
            putString(k, v)
        }
    }
}