package com.abcd.findyourdoctor.dashboard.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.dashboard.ui.home.entity.BannerBase
import com.abcd.findyourdoctor.databinding.FragmentHomeBinding
import com.abcd.findyourdoctor.doctor.DoctorListActivity
import com.abcd.findyourdoctor.serverrequest.MockRepository
import com.app.chatmodule.messaging.ChatConstant
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewPager: ViewPager2
    private lateinit var bannerBase : BannerBase

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initUI(root)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initUI(view: View) {
        bannerBase = MockRepository.getInstance().getData(activity, BannerBase::class.java, "banner_data.json")
        viewPager = view.findViewById(R.id.viewPager2)
        val layoutNearBy : MaterialCardView = view.findViewById(R.id.layoutNearByDoctors)
        val layoutOnlineDoctors : MaterialCardView = view.findViewById(R.id.layoutOnlineDoctors)
        layoutNearBy.setOnClickListener(View.OnClickListener {
            startActivity(Intent(activity, DoctorListActivity::class.java))
        })

        layoutOnlineDoctors.setOnClickListener {
            val intent = Intent(activity, DoctorListActivity::class.java)
            intent.putExtra(ChatConstant.FETCH_ONLINE, true)
            startActivity(intent)
        }

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        setTabLayout(tabLayout)
        setViewPager()
    }

    private fun setTabLayout(tabLayout: TabLayout) {
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            run {
//
//            }
//        }.attach()
    }

    private fun setViewPager() {
//        val drawableList: List<Int> = loginOptionViewModel.createDrawableList()
//        val imageViewPagerAdapter = ImageViewPagerAdapter(
//            requireActivity().getSupportFragmentManager(),
//            drawableList
//        )
//        viewPager.setAdapter(imageViewPagerAdapter)
    }
}