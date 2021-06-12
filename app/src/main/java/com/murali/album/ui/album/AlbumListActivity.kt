package com.murali.album.ui.album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.murali.album.R
import com.murali.album.databinding.ActivityAlbumListBinding
import com.murali.album.model.Album
import com.murali.album.utils.showToast
import com.murali.album.ui.albumdetails.AlbumDetailActivity
import com.murali.album.utils.ServerResponse
import com.murali.album.utils.isInternetAvailable
import com.murali.album.viewmodel.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumListBinding
    private val albumListViewModel: AlbumListViewModel by viewModels()
    @Inject
    lateinit var albumListAdapter: AlbumListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        setUpUI()
        setObserver()
    }

    private fun setUpUI() {
        recyclerView = binding.recyclerView
        progressBar = binding.progressDialog
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AlbumListActivity)
            setHasFixedSize(true)
            adapter = albumListAdapter
            addItemDecoration(DividerItemDecoration(this@AlbumListActivity, LinearLayout.VERTICAL))
        }

        albumListAdapter.onItemClick = { album ->
            val intent = Intent(this@AlbumListActivity, AlbumDetailActivity::class.java)
            intent.putExtra("album", album)
            startActivity(intent)
        }
    }

    private fun setObserver() {
        albumListViewModel.liveData.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    ServerResponse.Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { albums ->
                            showAlbums(albums as List<Album>)
                        }
                    }
                    ServerResponse.Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        showToast(it.message)
                    }
                    ServerResponse.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
        if (isInternetAvailable()) {
            albumListViewModel.getAlbums()
        } else {
            showToast(resources.getString(R.string.no_internet))
        }
    }

    fun showAlbums(list: List<Album>) {
        recyclerView.apply {
            with(adapter as AlbumListAdapter) {
                setData(list.toMutableList())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_album, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.queryHint = getString(R.string.searchHint)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    albumListAdapter.filter.filter(newText)
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

}
