package com.example.kickitaustin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var  gameAdapter: GameRecyclerAdapter

    companion object {
        const val TAG = "XXX"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authInitIntent = Intent(this, AuthenActivity::class.java)
        startActivity(authInitIntent)

        initRecyclerView()
        addDataSet()
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
