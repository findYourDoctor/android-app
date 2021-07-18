package com.app.chatmodule.messaging.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.chatmodule.R
import com.app.chatmodule.base.BaseFragment
import com.app.chatmodule.messaging.adapter.ActiveChatAdapter
import com.app.chatmodule.messaging.viewmodel.ActiveChatListViewModel

class ActiveChatFragment : BaseFragment() {

    private lateinit var notificationsViewModel: ActiveChatListViewModel
    private lateinit var emptyChatView : View
    private lateinit var recyclerChatList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(ActiveChatListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)

        recyclerChatList = root.findViewById(R.id.recyclerActiveChat)
        emptyChatView = root.findViewById(R.id.emptyChatView)
        getActiveChatList()
        observeChatListData()
        return root
    }

    private fun getActiveChatList() {
        showProgressDialog()
        notificationsViewModel.getActiveChatList()
    }

    private fun observeChatListData() {
        notificationsViewModel.activeChatLiveData.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            if (it.size == 0) {
                emptyChatView.visibility = View.VISIBLE
                recyclerChatList.visibility = View.GONE
            } else {
                emptyChatView.visibility = View.GONE
                recyclerChatList.visibility = View.VISIBLE
                val activeChatAdapter = ActiveChatAdapter(it)
                val manager = LinearLayoutManager(activity)
                recyclerChatList.layoutManager =
                    manager

                recyclerChatList.adapter = activeChatAdapter
            }
        })
    }
}