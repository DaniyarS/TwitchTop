package dev.dslam.twitchtop

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.twitchtop.adapter.TopGamesAdapter
import dev.dslam.twitchtop.viewmodels.TopGameViewModel
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var topGamesAdapter: TopGamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            initViewModel()
            swipeRefreshLayout.isRefreshing = false
        }

        btSendFeedback.setOnClickListener {

        }
    }

    private fun initRecyclerView() {
        rvTopGames.layoutManager = LinearLayoutManager(this)
        topGamesAdapter = TopGamesAdapter()
        rvTopGames.adapter = topGamesAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel:TopGameViewModel = ViewModelProvider(this)[TopGameViewModel::class.java]
        viewModel.getTopGamesObserver().observe(this, {
            if(it != null) {
                topGamesAdapter.setTopGamesList(it)
                topGamesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.loadTopGamesList()
    }
}