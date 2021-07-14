package com.abcd.findyourdoctor.messaging.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abcd.findyourdoctor.BaseActivity
import com.abcd.findyourdoctor.DoctorConstants
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.dashboard.ui.notifications.ActiveChatData
import com.abcd.findyourdoctor.doctor.entity.DoctorData
import com.abcd.findyourdoctor.messaging.ChatAdapter
import com.abcd.findyourdoctor.messaging.ChatViewModel
import com.abcd.findyourdoctor.messaging.entity.ChatData
import com.abcd.findyourdoctor.messaging.entity.SecondUserData
import com.abcd.findyourdoctor.util.MyScrollToBottomObserver
import com.abcd.findyourdoctor.util.SharedPreferenceUtil
import com.google.firebase.database.*
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList


class ChatFragment : Fragment() {

    private var userId: Long? = null
    private lateinit var chatRecycler: RecyclerView
    private lateinit var imgSend: ImageView
    private lateinit var editChat: EditText
    private lateinit var secondUserData: SecondUserData
    private lateinit var chatId : String
    private val database = FirebaseDatabase.getInstance().reference
    private var chatList : ArrayList<ChatData> = ArrayList()
    private lateinit var adapter : ChatAdapter

    companion object {
        fun newInstance(userData: SecondUserData) = ChatFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DoctorConstants.DOCTOR_DATA, userData)
            }
        }
    }

    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.message_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        userId = SharedPreferenceUtil.getLongPreferences(activity, "userId", 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatRecycler = view.findViewById(R.id.recyclerChat)
        editChat = view.findViewById(R.id.editChat)
        imgSend = view.findViewById(R.id.imgSend)
        chatTextWatcher()

        startObservingForMessages()
        imgSend.setOnClickListener {
            send(editChat.text.toString())
            editChat.text.clear()
        }
        setRecyclerAdapter()
    }

    private fun send(message: String) {
        val chatData = ChatData()
        chatData.message = message
        chatData.senderId = (activity as BaseActivity).getUserId()
        chatData.timestamp = System.currentTimeMillis()
        database.child("messages").child(chatId).push().setValue(chatData)
    }

    private fun updateActiveUsers(message: String, timestamp: Long) {
        val chatDataUser1 = ActiveChatData(
            message = message,
            timestamp = timestamp,
            userId = secondUserData.id!!,
            imageUrl = "",
            name = secondUserData.name!!
        )

        val chatDataUser2 = ActiveChatData(
            message = message,
            timestamp = timestamp,
            userId = userId.toString(),
            imageUrl = "",
            name = secondUserData.name!!
        )
        database.child("activeChats").child(userId.toString()).child(chatId).setValue(chatDataUser1)
        database.child("activeChats").child(secondUserData.id!!).child(chatId).setValue(chatDataUser2)
    }

    private fun startObservingForMessages() {
        database.child("messages").child(chatId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children
                try {
                    val messageObj = data.last().getValue(ChatData::class.java)
                    if (messageObj != null) {
                        Handler(Looper.getMainLooper()).post(Runnable {
                            updateActiveUsers(messageObj.message, messageObj.timestamp)
                        })

                    }
                } catch (ex : NoSuchElementException) {
                    Log.e("", "")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("", "")
            }

        })
        database.child("messages").child(chatId).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("firebase", "Got value ${snapshot.value}")
                val data = snapshot.getValue(ChatData::class.java)
                chatList.add(data!!)
                adapter.notifyItemInserted(chatList.size - 1)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("firebase", "Got value ${snapshot.value}")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.i("firebase", "Got value ${snapshot.value}")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("firebase", "Got value ${snapshot.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("firebase", "Got value ${error.message}")
            }

        })
    }

    private fun chatTextWatcher() {
        editChat.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isNotEmpty()) {
                    imgSend.visibility = View.VISIBLE
                } else {
                    imgSend.visibility = View.GONE
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    imgSend.visibility = View.VISIBLE
                } else {
                    imgSend.visibility = View.GONE
                }
            }

        })
    }

    private fun setRecyclerAdapter() {
        val manager = LinearLayoutManager(activity)
        manager.stackFromEnd = true
        chatRecycler.layoutManager =
            manager

        adapter = ChatAdapter(chatList, (activity as BaseActivity).getUserId())
        adapter.registerAdapterDataObserver(
            MyScrollToBottomObserver(chatRecycler, adapter, manager)
        )
        chatRecycler.adapter = adapter

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getParcelable<SecondUserData>(DoctorConstants.DOCTOR_DATA)?.let {
            secondUserData = it
            if (secondUserData.id != null) {
                chatId = (activity as BaseActivity).getChatId(secondUserData.id!!)
            }
        }
    }

}