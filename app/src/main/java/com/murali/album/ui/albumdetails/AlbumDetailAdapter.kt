package com.murali.album.ui.albumdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.murali.album.BR
import com.murali.album.R
import com.murali.album.databinding.LayoutAlbumDetailItemBinding
import com.murali.album.model.entities.AlbumDetail
import javax.inject.Inject

class AlbumDetailAdapter @Inject constructor(): RecyclerView.Adapter<AlbumDetailAdapter.ItemViewHolder>() {

    var itemList = mutableListOf<AlbumDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: LayoutAlbumDetailItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_album_detail_item, parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList.get(position))
    }

    open class ItemViewHolder(val itemBinding: LayoutAlbumDetailItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Any){
            itemBinding.setVariable(BR.albumDetailItem, data)
            itemBinding.executePendingBindings()
        }
    }

    fun setData(list: MutableList<AlbumDetail>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}