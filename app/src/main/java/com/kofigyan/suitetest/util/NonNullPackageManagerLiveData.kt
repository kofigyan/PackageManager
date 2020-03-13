package com.kofigyan.suitetest.util

import android.content.Context
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class NonNullPackageManagerLiveData(context: Context): PackageManagerLiveData(context) {

    fun observe(owner: LifecycleOwner, observer: PackagesStatusMessageObserver) {
        super.observe(owner, object : Observer<PackagesStatus> {
            override fun onChanged(@Nullable status: PackagesStatus?) {
                if (status == null ) {
                    return
                }

                when(status){
                    is PackagesStatus.Added -> observer.onNewMessage(status.message)
                    is PackagesStatus.Removed ->  observer.onNewMessage(status.message)
                    is PackagesStatus.Changed ->  observer.onNewMessage(status.message)
                }

            }
        })
    }


    interface PackagesStatusMessageObserver {
        fun onNewMessage(message: String)
    }

}