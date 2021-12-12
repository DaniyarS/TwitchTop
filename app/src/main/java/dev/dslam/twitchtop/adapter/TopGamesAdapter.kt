package dev.dslam.twitchtop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.dslam.twitchtop.R
import dev.dslam.twitchtop.models.TopGame
import kotlinx.android.synthetic.main.top_games_list_item.view.*

class TopGamesAdapter : RecyclerView.Adapter<TopGamesAdapter.TopGameViewHolder>() {

    private var topGamesList: List<TopGame>? = null

    fun setTopGamesList(listData: List<TopGame>) {
        this.topGamesList = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.top_games_list_item,
            parent,
            false)

        return TopGameViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopGameViewHolder, position: Int) {
        holder.bind(topGamesList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return topGamesList?.size ?: 0
    }

    inner class TopGameViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val thumbImage = view.ivBoxArt
        private val tvName = view.tvName

        fun bind(topGame: TopGame) {
            tvName.text = topGame.name

            Glide.with(thumbImage)
                .load(topGame.boxArtUrl)
                .into(thumbImage)
        }
    }
}
