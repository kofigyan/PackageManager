package com.kofigyan.suitetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.kofigyan.suitetest.adapter.PackageManagerAdapter
import com.kofigyan.suitetest.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected lateinit var binding: B

    protected abstract val viewModelClass: Class<VM>

    protected lateinit var packageAdapter: PackageManagerAdapter

    protected val viewModel: VM by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    protected fun setupRecyclerView(
        recyclerView: RecyclerView
    ) {
        packageAdapter = PackageManagerAdapter(this)
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = packageAdapter
        }
    }

}