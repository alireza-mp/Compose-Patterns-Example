package com.alirezamp.compose_patterns_example.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExperimentalOnlineChecker
constructor(
    private val context: Application,
    private val runtime: Runtime,
) : OnlineChecker {
    override suspend fun isOnline(): Boolean {
        if (isNetworkEnabled()) {
            try {
                return withContext(Dispatchers.IO) {
                    val ipProcess = runtime.exec("ping -c 1 alirezamomenpour.ir")
                    val exitValue = ipProcess.waitFor()
                    return@withContext exitValue == 0
                }
            } catch (e: Exception) {
                return false
            }
        } else return false
    }

    private fun isNetworkEnabled(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.getNetworkCapabilities(manager.activeNetwork)?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } ?: false
    }
}

interface OnlineChecker {
    suspend fun isOnline(): Boolean
}
