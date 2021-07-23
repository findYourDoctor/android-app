package com.app.chatmodule.messaging.adapter

import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.chatmodule.DoctorConstants
import com.app.chatmodule.R
import com.app.chatmodule.messaging.entity.ActiveChatData
import com.app.chatmodule.messaging.entity.SecondUserData
import com.app.chatmodule.messaging.ui.ChatActivity
import com.app.chatmodule.util.TimeUtil

class ActiveChatAdapter(private val activeChatList : ArrayList<ActiveChatData>) : RecyclerView.Adapter<ActiveChatAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textUserName : TextView = itemView.findViewById(R.id.textName)
        var textMessage : TextView = itemView.findViewById(R.id.textMessage)
        var txtDate : TextView = itemView.findViewById(R.id.txtTime)
        var imgProfile : ImageView = itemView.findViewById(R.id.imgProfilePic)
        var mView : View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.active_chat_adapter, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setData(position, holder)
        holder.mView.setOnClickListener {
            val doctorData = SecondUserData()
            doctorData.id = activeChatList[position].userId
            doctorData.name = activeChatList[position].name
            val intent = Intent(it.context, ChatActivity::class.java)
            intent.putExtra(DoctorConstants.DOCTOR_DATA, doctorData)
            it.context.startActivity(intent)
        }
    }

    private fun setData(position: Int, holder: ViewHolder) {
        holder.textUserName.text = activeChatList[position].name
        holder.textMessage.text = activeChatList[position].message
        holder.txtDate.text = TimeUtil.getFormattedDate(activeChatList[position].timestamp)
//        if (activeChatList[position].chatCount > 0) {
//            holder.textMessage.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
//        } else {
//            holder.textMessage.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
//        }
    }

    override fun getItemCount(): Int {
        return activeChatList.size
    }
}