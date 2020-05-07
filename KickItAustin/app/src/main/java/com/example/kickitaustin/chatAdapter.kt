package com.example.kickitaustin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.chat_row.view.*

class chatAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var chats: List<String> = ArrayList()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSentCurrentUser = itemView.textSentCurrentUser
        val textOwnerCurrentUser = itemView.textOwnerCurrentUser
        val textSentOther = itemView.textSentOther
        val textOwnerOther = itemView.textOwnerOther

        fun bind(chat: String) {
            val strings = chat.split("@")
            //Log.d("XXX", strings.toString())
            if (!chat.equals("Hello welcome to the chat!")) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                Log.d("XXX", strings.toString())
                if (currentUser!!.uid.equals(strings[2])) {
                    textSentOther.visibility = View.GONE
                    textOwnerOther.visibility = View.GONE

                    textSentCurrentUser.text = strings[0]
                    textOwnerCurrentUser.text = strings[1]
                } else {
                    textSentCurrentUser.visibility = View.GONE
                    textOwnerCurrentUser.visibility = View.GONE

                    textSentOther.text = strings[0]
                    textOwnerOther.text = strings[1]
                }
            }
            else {
                //Log.d("XXX","String equals first one Hello to chat")
                textSentOther.visibility = View.GONE
                textOwnerOther.visibility = View.GONE
                textOwnerCurrentUser.visibility = View.GONE
                textSentCurrentUser.text = chat
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatAdapter.VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.chat_row, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VH).bind(chats[position])
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    fun submitList(listOfChats: List<String>) {
        chats = listOfChats
    }
}