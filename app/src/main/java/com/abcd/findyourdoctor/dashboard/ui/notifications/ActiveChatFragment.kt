package com.abcd.findyourdoctor.dashboard.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abcd.findyourdoctor.BaseActivity
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.databinding.FragmentChatBinding

class ActiveChatFragment : Fragment() {

    private lateinit var notificationsViewModel: ActiveChatListViewModel
    private var _binding: FragmentChatBinding? = null
    private lateinit var emptyChatView : View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(ActiveChatListViewModel::class.java)
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        emptyChatView = root.findViewById(R.id.emptyChatView)
        getActiveChatList()
        observeChatListData()
        return root
    }

    private fun getActiveChatList() {
        (activity as BaseActivity).showProgressDialog()
        notificationsViewModel.getActiveChatList()
    }

    private fun observeChatListData() {
        val recyclerChatList: RecyclerView = binding.recyclerActiveChat
        notificationsViewModel.activeChatLiveData.observe(viewLifecycleOwner, Observer {
            (activity as BaseActivity).hideProgressDialog()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}