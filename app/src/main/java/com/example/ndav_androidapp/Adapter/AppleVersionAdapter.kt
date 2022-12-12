package com.example.ndav_androidapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ndav_androidapp.databinding.ItemCustomFooterBinding
import com.example.ndav_androidapp.databinding.ItemCustomHeaderBinding
import com.example.ndav_androidapp.databinding.ItemCustomRecyclerBinding
import com.example.ndav_androidapp.models.DataSourceFooterSample
import com.example.ndav_androidapp.models.DataSourceHeaderSample
import com.example.ndav_androidapp.models.DataSourceSample
import com.example.ndav_androidapp.models.MyObjectForRecyclerView

private val diffItemUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {

    override fun areItemsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }
}

class AppleVersionAdapter(
    private val onItemClick: (objectDataSample: DataSourceSample, view: View) -> Unit
): ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataSourceSample -> MyItemType.ROW.type
            is DataSourceHeaderSample -> MyItemType.HEADER.type
            is DataSourceFooterSample -> MyItemType.FOOTER.type
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as AppleVersionViewHolder).bind(getItem(position) as DataSourceSample)

            MyItemType.HEADER.type -> (holder as AppleVersionHeaderViewHolder).bind(getItem(position) as DataSourceHeaderSample)

            MyItemType.FOOTER.type -> (holder as AppleVersionFooterViewHolder).bind(getItem(position) as DataSourceFooterSample)

            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MyItemType.ROW.type -> {
                AppleVersionViewHolder(
                    ItemCustomRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
                )
            }

            MyItemType.HEADER.type -> {
                AppleVersionHeaderViewHolder(
                    ItemCustomHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MyItemType.FOOTER.type -> {
                AppleVersionFooterViewHolder(
                    ItemCustomFooterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }

}


class AppleVersionViewHolder(
    private val binding: ItemCustomRecyclerBinding,
    private val onItemClick: (objectDataSample: DataSourceSample, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var ui: DataSourceSample

    init{
        binding.root.setOnClickListener{
            onItemClick(ui, itemView)
        }
    }

    fun bind(objectDataSample: DataSourceSample) {
        ui = objectDataSample
        binding.DataSourceModele.text = objectDataSample.modele
        binding.DataSourceAnnee.text = "${objectDataSample.annee}"
    }
}

class AppleVersionHeaderViewHolder(
    private val binding: ItemCustomHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(objectDataHeaderSample: DataSourceHeaderSample){
        binding.itemRecyclerViewHeader.text = objectDataHeaderSample.header
    }
}

class AppleVersionFooterViewHolder(
    private val binding: ItemCustomFooterBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(objectDataFooterBinding: DataSourceFooterSample) {
        binding.itemRecyclerViewFooter.text = objectDataFooterBinding.footer
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}
