package com.murali.album.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.murali.album.BR
import com.murali.album.R
import com.murali.album.databinding.LayoutAlbumItemBinding
import com.murali.album.model.entities.Album
import javax.inject.Inject

class AlbumListAdapter @Inject constructor(): RecyclerView.Adapter<AlbumListAdapter.ItemViewHolder>(), Filterable {
    var albumList = mutableListOf<Album>()
    var albumListFull = mutableListOf<Album>()
    var onItemClick: ((Album) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: LayoutAlbumItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_album_item, parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = albumList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(albumList.get(position))
        holder.itemBinding.root.setOnClickListener {
            onItemClick?.invoke(albumList[position])
        }
    }

    inner class ItemViewHolder(val itemBinding: LayoutAlbumItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Any){
            itemBinding.setVariable(BR.AlbumItem, data)
            itemBinding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return albumFilter
    }

    private val albumFilter = object : Filter() {
        //runs on background thread
        override fun performFiltering(charsequence: CharSequence?): FilterResults {
            var filteredList = listOf<Album>()
            if(charsequence == null || charsequence.isEmpty()){
                filteredList = albumListFull.toList()
            }else{
                filteredList = albumListFull.filter { album: Album ->
                    album.title.lowercase().contains(charsequence.toString().lowercase())
                }
            }
            return FilterResults().apply { values = filteredList }
        }

        //runs on UI thread
        override fun publishResults(charsequence: CharSequence?, filterResults: FilterResults?) {
            albumList.clear()
            if(filterResults?.values as? MutableList<Album> != null) {
                albumList.addAll(filterResults.values as MutableList<Album>)
                notifyDataSetChanged()
            }
        }
    }

    fun setData(list: MutableList<Album>){
        albumList.clear()
        albumListFull.clear()
        albumList.addAll(list)
        albumListFull.addAll(list)
        notifyDataSetChanged()
    }
}