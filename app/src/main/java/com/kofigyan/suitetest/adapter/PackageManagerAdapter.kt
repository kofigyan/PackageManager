package com.kofigyan.suitetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kofigyan.suitetest.databinding.ListItemPackageBinding
import com.kofigyan.suitetest.models.PackageInfo
import androidx.recyclerview.widget.DiffUtil


class PackageManagerAdapter internal constructor(
    val context: Context
) : ListAdapter<PackageInfo, PackageManagerAdapter.PackageViewHolder>(
    PackageDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        return PackageViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }


    class PackageViewHolder constructor(private val binding: ListItemPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PackageInfo, context: Context) {

            binding.packageList = item

            binding.pkgLabel.text = item.label
            binding.pkgName.text = item.name
            binding.pkgVersion.text = item.versionName

            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): PackageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPackageBinding.inflate(layoutInflater, parent, false)
                return PackageViewHolder(
                    binding
                )
            }
        }
    }


    class PackageDiffCallback : DiffUtil.ItemCallback<PackageInfo>() {
        override fun areItemsTheSame(
            oldItem: PackageInfo,
            newItem: PackageInfo
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: PackageInfo,
            newItem: PackageInfo
        ) = oldItem == newItem
    }


}