package com.murali.album.ui.albumdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.murali.album.R
import com.murali.album.databinding.ActivityAlbumDetailBinding
import com.murali.album.model.Album
import com.murali.album.model.AlbumDetail
import com.murali.album.utils.ServerResponse
import com.murali.album.utils.isInternetAvailable
import com.murali.album.utils.loadImage
import com.murali.album.utils.showToast
import com.murali.album.viewmodel.AlbumDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumDetailBinding
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()
    private lateinit var album: Album
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    @Inject lateinit var albumDetailAdapter: AlbumDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_detail)
        setUpUI()
        setObserver()
    }

    private fun setObserver() {
        albumDetailViewModel.liveData.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    ServerResponse.Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { albums ->
                            showDetails(albums as List<AlbumDetail>)
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
            albumDetailViewModel.getAlbumDetails()
        } else {
            showToast(resources.getString(R.string.no_internet))
        }
    }

    private fun showDetails(list: List<AlbumDetail>) {
        recyclerView.apply {
            with(adapter as AlbumDetailAdapter) {
                setData(list.toMutableList())
            }
        }
    }

    private fun setUpUI() {
        album = intent.extras?.get("album") as Album
        loadImage(binding.albumImage, album.url)
        recyclerView = binding.recyclerView
        progressBar = binding.progressDialog
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@AlbumDetailActivity, 2)
            adapter = albumDetailAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@AlbumDetailActivity,
                    LinearLayout.VERTICAL
                )
            )
        }
    }
}
