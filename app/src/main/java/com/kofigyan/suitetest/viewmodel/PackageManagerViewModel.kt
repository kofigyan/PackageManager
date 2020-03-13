package com.kofigyan.suitetest.viewmodel

import android.app.Application
import com.kofigyan.suitetest.util.NonNullPackageManagerLiveData

class PackageManagerViewModel(application: Application) : BaseViewModel(application) {

    val packageStatusLiveData  = NonNullPackageManagerLiveData(application)

}