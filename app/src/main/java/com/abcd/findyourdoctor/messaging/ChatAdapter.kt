package com.abcd.findyourdoctor.messaging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.messaging.entity.ChatData

class ChatAdapter(private val chatList : ArrayList<ChatData>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private val userId : String = ""
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var textChatSender : TextView
        var textChatUser : TextView
        var textTimeSender : TextView
        var textTimeUser : TextView
        var layoutLeft : LinearLayout
        var layoutRight :  LinearLayout

        init {
            textChatSender = view.findViewById(R.id.senderMessage)
            textChatUser = view.findViewById(R.id.userMessage)
            textTimeSender = view.findViewById(R.id.timeSender)
            textTimeUser = view.findViewById(R.id.timeUser)
            layoutLeft = view.findViewById(R.id.layoutLeft)
            layoutRight = view.findViewById(R.id.layoutRight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 0/*chatList[position].senderId == userId*/) {
            holder.layoutLeft.visibility = View.VISIBLE
            holder.layoutRight.visibility = View.GONE

//            holder.textChatUser.text = chatList[position].message
//            holder.textTimeUser.text = chatList[position].timestamp
        } else {
            holder.layoutLeft.visibility = View.GONE
            holder.layoutRight.visibility = View.VISIBLE

//            holder.textChatSender.text = chatList[position].message
//            holder.textTimeUser.text = chatList[position].timestamp
        }
    }

    override fun getItemCount(): Int {
        return /*chatList.size*/15
    }
}