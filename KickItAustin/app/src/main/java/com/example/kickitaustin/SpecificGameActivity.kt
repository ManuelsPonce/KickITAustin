package com.example.kickitaustin

import ViewModel
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_specific_game.*
import kotlinx.android.synthetic.main.list_item.*

class SpecificGameActivity : AppCompatActivity() {

    private val viewModel: ViewModel = ViewModel()
    val firebaseAuthInstance = FirebaseAuth.getInstance()
    val currentUser = firebaseAuthInstance.currentUser
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var  chatAdapter: chatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_game)

       // val intent = Intent(this, SpecificGameActivity::class.java)
        val gamePost = intent.extras!!.get("gamePost") as GamePost

        Log.d("XXX", gamePost.toString())
        //Log.d("XXX", "inspecific game on creaete")

        //binding all texts
        addAllText(gamePost)


        //To go back to main page.
        goBackBut.setOnClickListener {
            finish()
        }

        if(gamePost.ownerUid == currentUser!!.uid) {
            deleteButt.visibility = View.VISIBLE
            deleteButt.setOnClickListener {
                deleteGameRow(gamePost)
            }

            addButton.visibility = View.GONE
            iWillAttend.text = ""
        }
        else {
            if(gamePost.listOfAttendents!!.contains(currentUser.uid)) {
                iWillAttend.text = "You have RSVP'd for game!"
                iWillAttend.setTextColor(Color.parseColor("#00FF00"))
                addButton.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp)
            }

            addButton.setOnClickListener {
                if (!gamePost.listOfAttendents!!.contains(currentUser.uid)) {
                    iWillAttend.text = "You have RSVP'd for game!"
                    iWillAttend.setTextColor(Color.parseColor("#00FF00"))
                    it.setBackgroundResource(R.drawable.ic_indeterminate_check_box_black_24dp)
                    gamePost.numberOfPlayers = gamePost.numberOfPlayers !!+ 1
                    gamePost!!.listOfAttendents!!.add(currentUser.uid)
                    Log.d("XXX", "Number Of players: " + gamePost.numberOfPlayers)
                    updateAttendents(gamePost)
                }
                else {
                    iWillAttend.text = "RSVP for game!"
                    iWillAttend.setTextColor(Color.parseColor("#000000"))
                    it.setBackgroundResource(R.drawable.ic_add_circle_black_24dp)
                    gamePost.numberOfPlayers = gamePost.numberOfPlayers !!- 1
                    Log.d("XXX", "Number Of players: " + gamePost.numberOfPlayers)
                    gamePost!!.listOfAttendents!!.remove(currentUser.uid)
                    updateAttendents(gamePost)
                }
            }
        }

        //Chat stuff
        initRecyclerView(gamePost)
        chatAdapter.submitList(gamePost!!.listOfTextChats!!.toList())
        chatAdapter.notifyDataSetChanged()

        viewModel.observeTexts().observe(this, Observer {
            Log.d("XXX", "Observe GamePost $it")
            chatAdapter.submitList(it)
            chatAdapter.notifyDataSetChanged()
        })

        composeSendIB.setOnClickListener {
            if(composeMessageET.text.isNotEmpty()) {
//                val textChat = TextChats().apply {
//                    message = composeMessageET.text.toString()
//                    ownerUid = currentUser.uid
//                    gameOwner = currentUser.displayName
//                }
                val message = composeMessageET.text.toString()
                if (message.contains("@")) {
                    Snackbar.make(it, "Text entered cannot contain a '@'", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
                else {
                    gamePost.listOfTextChats!!.add(message + "@" + currentUser.displayName + "@" + currentUser.uid)
                    updateTexts(gamePost)
                    chatAdapter.submitList(gamePost!!.listOfTextChats!!.toList())
                    chatAdapter.notifyDataSetChanged()
                    composeMessageET.text.clear()
                }
            }
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
                specificDOG.text = gamePost.dateOfGame

                //Profile picture
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                //Log.d("XXX", "Profile picture" + gamePost.profilePic.toString())
                Glide.with(this)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(gamePost.profilePic)
                    .into(specificProfilePicture)

            }
    }

    private fun updateTexts(gamePost: GamePost) {
        val gampostId = gamePost.rowId
        db
            .collection("gamePost").document(gampostId).update("listOfTextChats", gamePost.listOfTextChats)
            .addOnSuccessListener { Log.d("XXX", "Updated list of Texts") }
            .addOnFailureListener{e -> Log.d("XXX", "failedUpdatingListOfTexts")}
}

    private fun updateAttendents(gamePost: GamePost) {
        val gampostId = gamePost.rowId
        db
            .collection("gamePost").document(gampostId).update("numberOfPlayers", gamePost.numberOfPlayers)
            .addOnSuccessListener { Log.d("XXX", "updateOfPlayersSuccesful") }
            .addOnFailureListener{e -> Log.d("XXX", "failed updating num players going")}

        db
            .collection("gamePost").document(gampostId).update("listOfAttendents", gamePost.listOfAttendents)
            .addOnSuccessListener { Log.d("XXX", "updateOfPlayersSuccesful") }
            .addOnFailureListener{e -> Log.d("XXX", "failed updating num players going")}
        specificAttending.text = gamePost.numberOfPlayers.toString()
    }

    fun deleteGameRow(gamePost: GamePost){
        // Delete picture (if any) on the server, asynchronously
        val uuid = gamePost.profilePic
        if(uuid != null) {
            //Storage.deleteImage(uuid)
        }
        Log.d(javaClass.simpleName, "remote chatRow id: ${gamePost.rowId}")

        // XXX delete chatRow
        val gampostId = gamePost.rowId
        db.collection("gamePost").document(gampostId).delete()
            .addOnSuccessListener {
                //
                finish()
            }
    }

    private fun initRecyclerView(gamePost: GamePost) {
        recyclerViewForChat.apply {
            layoutManager = LinearLayoutManager(this@SpecificGameActivity)
            chatAdapter = chatAdapter()
            adapter = chatAdapter
        }
    }
}
