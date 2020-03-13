package com.kofigyan.suitetest.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData

open class PackageManagerLiveData(private val context: Context) : LiveData<PackagesStatus>() {

    override fun onActive() {
        registerReceiver()
    }

    override fun onInactive() {
        unregisterReceiver()
    }


    private fun registerReceiver() = context.registerReceiver(
        packageStatusReceiver,
        IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addDataScheme("package")
        }
    )

    private fun unregisterReceiver() = context.unregisterReceiver(packageStatusReceiver)

   private val packageStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val data = intent.getData()

            data?.getScheme()?.let {
                val pkgName =
                    data.toString().substring(it.length + 1) //remove 'package:'
                if (intent.getAction() === "android.intent.action.PACKAGE_ADDED") {
                    postValue(PackagesStatus.Added("$pkgName  is installed"))
                } else if (intent.getAction() === "android.intent.action.PACKAGE_REMOVED") {
                    postValue(PackagesStatus.Removed("$pkgName  is uninstalled"))
                } else if (intent.getAction() === "android.intent.action.PACKAGE_CHANGED") {
                    postValue(PackagesStatus.Changed("$pkgName  is changed"))
                }
            }
        }
    }

}

sealed class PackagesStatus {
    data class Added(val message: String) : PackagesStatus()
    data class Changed(val message: String) : PackagesStatus()
    data class Removed(val message: String) : PackagesStatus()
}