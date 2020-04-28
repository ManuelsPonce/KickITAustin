package com.example.kickitaustin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_game.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var  gameAdapter: GameRecyclerAdapter

    companion object {
        const val TAG = "XXX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Authentication Activity
        val authInitIntent = Intent(this, AuthenActivity::class.java)
        startActivity(authInitIntent)

        initRecyclerView()
        addDataSet()

        //Create game activity
        floatingActionButton.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

            startActivity(Intent(this,CreateGame::class.java))
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

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        gameAdapter.submitList(data)
    }
}
