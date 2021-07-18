package com.abcd.findyourdoctor.messaging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.messaging.entity.ChatData
import com.abcd.findyourdoctor.util.TimeUtil

class ChatAdapter(private val chatList: ArrayList<ChatData>, private val userId : String
) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var textChatSender : TextView
        var textChatUser : TextView
        var txtDate : TextView
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
            txtDate = view.findViewById(R.id.txtDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when {
            position == 0 -> {
                holder.txtDate.visibility = View.VISIBLE
                holder.txtDate.text = TimeUtil.getFormattedDate(holder.textTimeUser.context, chatList[position].timestamp)
            }
            TimeUtil.getFormattedDate(holder.textTimeUser.context, chatList[position - 1].timestamp).equals(TimeUtil.getFormattedDate(holder.textTimeUser.context, chatList[position].timestamp)) -> {
                holder.txtDate.visibility = View.GONE
            }
            else -> {
                holder.txtDate.visibility = View.VISIBLE
                holder.txtDate.text = TimeUtil.getFormattedDate(holder.textTimeUser.context, chatList[position].timestamp)
            }
        }
        if (chatList[position].senderId == userId) {
            holder.layoutLeft.visibility = View.GONE
            holder.layoutRight.visibility = View.VISIBLE

            holder.textChatUser.text = chatList[position].message
            holder.textTimeUser.text = TimeUtil.getTimeFromTimeStamp(holder.textTimeUser.context, chatList[position].timestamp)
        } else {
            holder.layoutLeft.visibility = View.VISIBLE
            holder.layoutRight.visibility = View.GONE

            holder.textChatSender.text = chatList[position].message
            holder.textTimeSender.text = TimeUtil.getTimeFromTimeStamp(holder.textTimeUser.context, chatList[position].timestamp)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}