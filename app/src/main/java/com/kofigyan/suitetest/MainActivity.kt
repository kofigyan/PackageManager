package com.kofigyan.suitetest

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.kofigyan.suitetest.databinding.ActivityMainBinding
import com.kofigyan.suitetest.models.PackageInfo
import com.kofigyan.suitetest.util.NonNullPackageManagerLiveData
import com.kofigyan.suitetest.viewmodel.PackageManagerViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<PackageManagerViewModel, ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main


    override val viewModelClass: Class<PackageManagerViewModel>
        get() = PackageManagerViewModel::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        setupRecyclerView(recyclerview)

        populatePackages()

        subscribeToPackageListener()

    }

   private fun populatePackages() {
        val pkgList = mutableListOf<PackageInfo>()
        val pm = packageManager
        val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {
            with(packageInfo){
                pkgList.add(
                    PackageInfo(
                        packageName,
                        packageName,
                        versionName,
                        versionCode
                    )
                )
            }

        }
        packageAdapter.submitList(pkgList)
    }


    private fun subscribeToPackageListener() {
        viewModel.packageStatusLiveData.observe(this, packageStatusMessageObserver)
    }

    private val packageStatusMessageObserver =
        object : NonNullPackageManagerLiveData.PackagesStatusMessageObserver {
            override fun onNewMessage(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                populatePackages()
            }
        }

}
