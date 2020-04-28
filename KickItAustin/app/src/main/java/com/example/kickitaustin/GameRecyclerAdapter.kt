package com.example.kickitaustin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_item.view.*

class GameRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    private var items: List<GamePost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GameViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is GameViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<GamePost>){
        items = blogList
    }

    class GameViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val profilePic = itemView.profilePicture
        val location = itemView.location
        val gameOwner = itemView.gameOwner
        val actualStartTime = itemView.actualTime
        val numAttending = itemView.actLevelofPlay

        fun bind(gamePost: GamePost){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(gamePost.profilePic)
                .into(profilePic)
            location.text = gamePost.location
            gameOwner.text = gamePost.gameOwner
            actualStartTime.text = gamePost.startTime
            numAttending.text = gamePost.numberOfPlayers.toString()


        }

    }

}