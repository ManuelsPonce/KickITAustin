package com.example.kickitaustin

import ViewModel
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var  gameAdapter: GameRecyclerAdapter
    private val viewModel: ViewModel = ViewModel()
    private var gamePost = MutableLiveData<List<GamePost>>()
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val TAG = "XXX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Authentication Activity
        val authInitIntent = Intent(this, AuthenActivity::class.java)
        startActivity(authInitIntent)

        initRecyclerView()
        //addDataSet()

        logoutButt.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                val authInitIntent = Intent(this, AuthenActivity::class.java)
                startActivity(authInitIntent)
            }
        }

        db.collection("gamePost")
            .limit(100)
            .orderBy("startTime")
            .addSnapshotListener { querySnapshot, ex ->
                if (ex != null) {
                    Log.w(MainActivity.TAG, "listen:error", ex)
                    return@addSnapshotListener
                }
                Log.d(MainActivity.TAG, "fetch in main activty ${querySnapshot!!.documents.size}")
                gamePost.value = querySnapshot.documents.mapNotNull {
                    it.toObject(GamePost::class.java)
                }
                //Log.d("XXX",  "List in main activyty: " + gamePost.value.toString())
                gameAdapter.submitList(gamePost.value!!)
                gameAdapter.notifyDataSetChanged()
                //Log.d("XXX", "THE FUCKING LislT in adaptor: " + gameAdapter)
            }

            viewModel.observeData().observe(this, Observer {
                Log.d("XXX", "Observe GamePost $it")
                gameAdapter.submitList(it)
                gameAdapter.notifyDataSetChanged()
            })

        //Create game activity
        floatingActionButton.setOnClickListener { view ->
//            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()

            startActivity(Intent(this,CreateGameActivity::class.java))
        }
    }


    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(TopSpacingItemDecoration(30))
            gameAdapter = GameRecyclerAdapter()
            adapter = gameAdapter
        }
    }

//    private fun addDataSet(/*gameList: List<GamePost>*/){
//        //val data = DataSource.createDataSet(gameList)
//        //val dataTwo = DataSource.createDataSetTwo()
//        //gameAdapter.submitList(dataTwo)
//        //gameAdapter.notifyDataSetChanged()
//    }

}
