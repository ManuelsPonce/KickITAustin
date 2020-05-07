package com.example.kickitaustin


import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_specific_game.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.actAttending
import kotlinx.android.synthetic.main.list_item.view.actualTime
import kotlinx.android.synthetic.main.list_item.view.gameOwner
import kotlinx.android.synthetic.main.list_item.view.location
import kotlinx.android.synthetic.main.list_item.view.profilePicture

class GameRecyclerAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    private var items: List<GamePost> = ArrayList()

    class GameViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val profilePic = itemView.profilePicture
        val location = itemView.location
        val gameOwner = itemView.gameOwner
        val actualStartTime = itemView.actualTime
        val numAttending = itemView.actAttending

        fun bind(gamePost: GamePost) {

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


//        when (holder) {
//            is GameViewHolder -> {
//                Log.d(
//                    "XXX",
//                    "inside on bindViewHolder and GamePost i in list: " + items[position].toString()
//                )
        (holder as GameViewHolder).bind(items[position])
        holder.itemView.setOnClickListener {
//            Snackbar.make(it, "Clicked on item: " + position.toStrireddng(), Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()

            //SpecificGame Activity
            val specificGameIntent = Intent(it.context, SpecificGameActivity::class.java)
            specificGameIntent.putExtra("gamePost", items[position])

            //Log.d("XXX", "extras right before starting act: " + specificGameIntent.extras.toString())

           it.context.startActivity(specificGameIntent)
        }

//            }
//            else -> {
//                Log.d("XXX", "Not a GameViewHolder")
//                val profilePic = holder.itemView.profilePicture
//                val location = holder.itemView.location
//                val gameOwner = holder.itemView.gameOwner
//                val actualStartTime = holder.itemView.actualTime
//                val numAttending = holder.itemView.actAttending
//
//                val requestOptions = RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .error(R.drawable.ic_launcher_background)
//
//                Glide.with(holder.itemView.context)
//                    .applyDefaultRequestOptions(requestOptions)
//                    .load(items[position].profilePic)
//                    .into(profilePic)
//                location.text = items[position].location
//                gameOwner.text = items[position].gameOwner
//                actualStartTime.text = items[position].startTime
//                numAttending.text = items[position].numberOfPlayers.toString()
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        // Create a new View
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return GameViewHolder(v)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(gameList: List<GamePost>) {
        items = gameList
    }
}