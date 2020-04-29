package com.example.kickitaustin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.*

class SpecificGameActivity : AppCompatActivity() {

    private var firebaseAuthLiveData = FirestoreAuthLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_game)


//        moreBut.setOnClickListener {
//            val previous = viewModel.selected
//            val position = adapterPosition
//            // Manipulate ViewModel here because this call back comes directly from
//            // View (though ViewHolder is an inner class)
//            viewModel.selected = position
//            if (previous >= 0)
//                notifyItemChanged(previous)
//            notifyItemChanged(position)
//            val context = it.context
//            val item = viewModel.getListAt(position)
//            item?.let {
//                val selected = "You selected $position ${it.name}"
//                Toast.makeText(context, selected, Toast.LENGTH_SHORT).show()
//            }
//        }
//        abstract fun bind(item: Data?)

    }
}
