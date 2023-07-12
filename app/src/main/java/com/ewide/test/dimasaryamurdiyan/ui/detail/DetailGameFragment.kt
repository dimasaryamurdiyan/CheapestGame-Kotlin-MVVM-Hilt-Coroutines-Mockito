package com.ewide.test.dimasaryamurdiyan.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.databinding.FragmentDetailGameBinding


class DetailGameFragment : Fragment() {

    private lateinit var binding: FragmentDetailGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding.root
    }


}