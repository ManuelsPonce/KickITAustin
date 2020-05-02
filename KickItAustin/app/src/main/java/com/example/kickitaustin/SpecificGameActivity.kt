package com.example.kickitaustin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_specific_game.*
import kotlinx.android.synthetic.main.list_item.*

class SpecificGameActivity : AppCompatActivity() {

    val firebaseAuthInstance = FirebaseAuth.getInstance()
    val currentUser = firebaseAuthInstance.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_game)

       // val intent = Intent(this, SpecificGameActivity::class.java)
        val gamePost = intent.extras!!.get("gamePost")

        Log.d("XXX", gamePost.toString())
        //Log.d("XXX", "inspecific game on creaete")

        addAllText(gamePost as GamePost)

        //To go back to main page.
        goBackBut.setOnClickListener {
            finish()
        }

    }

    private fun addAllText(gamePost: GamePost) {
            if(gamePost !=  null ) {
//                specificLocation.text = bundle!!.getString("location")
//                specificTime.text = bundle!!.getString("startTime")
//                specificGameOwner.text = bundle!!.getString("gameOwner")
//                specificAttending.text = bundle!!.getString("numberOfPlayers")
//                specificLOP.text = bundle!!.getString("levelOflay")

                specificLocation.text = gamePost.location
                specificTime.text = gamePost.startTime
                specificGameOwner.text = gamePost.gameOwner
                specificAttending.text = gamePost.numberOfPlayers.toString()
                specificLOP.text = gamePost.levelOflay

                //Profile picture
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(this)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(gamePost.profilePic)
                    .into(specificProfilePicture)

            }
    }
}
