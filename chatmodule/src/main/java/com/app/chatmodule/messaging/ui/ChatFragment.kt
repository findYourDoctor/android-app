package com.app.chatmodule.messaging.ui

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.chatmodule.DoctorConstants
import com.app.chatmodule.R
import com.app.chatmodule.base.BaseFragment
import com.app.chatmodule.messaging.ChatConstant
import com.app.chatmodule.messaging.adapter.ChatAdapter
import com.app.chatmodule.messaging.entity.ActiveChatData
import com.app.chatmodule.messaging.entity.ChatData
import com.app.chatmodule.messaging.entity.SecondUserData
import com.app.chatmodule.messaging.viewmodel.ChatViewModel
import com.app.chatmodule.util.MyScrollToBottomObserver
import com.app.chatmodule.util.SharedPreferenceUtil
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class ChatFragment : BaseFragment() {

    private var mUserId: String? = null
    private lateinit var chatRecycler: RecyclerView
    private lateinit var imgSend: ImageView
    private lateinit var editChat: EditText
    private lateinit var secondUserData: SecondUserData
    private lateinit var chatId : String
    private val database = FirebaseDatabase.getInstance().reference
    private var chatList : ArrayList<ChatData> = ArrayList()
    private lateinit var adapter : ChatAdapter
    private lateinit var viewModel: ChatViewModel

    companion object {
        fun newInstance(userData: SecondUserData) = ChatFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DoctorConstants.DOCTOR_DATA, userData)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.message_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatRecycler = view.findViewById(R.id.recyclerChat)
        editChat = view.findViewById(R.id.editChat)
        imgSend = view.findViewById(R.id.imgSend)
        chatTextWatcher()
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        mUserId = SharedPreferenceUtil.getPreferences(activity, ChatConstant.USER_ID, "")
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
        chatData.senderId = getUserId()
        chatData.timestamp = System.currentTimeMillis()
        database.child(ChatConstant.MESSAGES).child(chatId).push().setValue(chatData)
    }

    private fun startObservingForMessages() {
        viewModel.updateActiveChats(chatId, secondUserData)

        database.child(ChatConstant.MESSAGES).child(chatId).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(ChatData::class.java)
                chatList.add(data!!)
                adapter.notifyItemInserted(chatList.size - 1)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("FirebaseChat", "Got value ${snapshot.value}")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.i("FirebaseChat", "Got value ${snapshot.value}")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("FirebaseChat", "Got value ${snapshot.value}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("FirebaseChat", "Got value ${error.message}")
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

        adapter = ChatAdapter(chatList, getUserId())
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
                chatId = getChatId(secondUserData.id!!)
            }
        }
    }

}