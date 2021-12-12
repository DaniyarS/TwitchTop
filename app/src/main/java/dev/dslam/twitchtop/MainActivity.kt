package dev.dslam.twitchtop

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.twitchtop.adapter.TopGamesAdapter
import dev.dslam.twitchtop.viewmodels.TopGameViewModel
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FeedbackDialog.ActionListener {
    private lateinit var topGamesAdapter: TopGamesAdapter
    private val feedBackDialog = FeedbackDialog(this)

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
            openFeedbackDialog()
        }
    }

    private fun openFeedbackDialog() {
        feedBackDialog.show(supportFragmentManager, "feedback dialog")
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
            if(!it.isNullOrEmpty()) {
                topGamesAdapter.setTopGamesList(it)
                topGamesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Упс, данные потерялись:)", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.loadTopGamesList()
    }

    override fun onClosePressed() {
        feedBackDialog.dismiss()
    }

    override fun onSendPressed(text: String) {
        feedBackDialog.dismiss()
        Toast.makeText(this, "Спасибо за отзыв", Toast.LENGTH_SHORT).show()
    }
}
