package com.ewide.test.dimasaryamurdiyan.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.databinding.FragmentHomeBinding
import com.ewide.test.dimasaryamurdiyan.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val titles = arrayOf("Games", "Favorite")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.vpHome.adapter =
            ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        TabLayoutMediator(binding.tlHome, binding.vpHome) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

}