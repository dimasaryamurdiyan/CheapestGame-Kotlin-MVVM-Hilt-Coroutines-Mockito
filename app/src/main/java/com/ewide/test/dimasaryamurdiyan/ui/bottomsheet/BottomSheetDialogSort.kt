package com.ewide.test.dimasaryamurdiyan.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.databinding.BottomSheetDialogSortBinding
import com.ewide.test.dimasaryamurdiyan.utils.Sort
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialogSort(private val listener: OnClickedListener): BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogSortBinding

    companion object {
        const val TAG = "BottomSheetDialog"

        fun newInstance(listener: OnClickedListener): BottomSheetDialogFragment {
            val fragment = BottomSheetDialogSort(listener)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetDialogSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewBind()
    }

    override fun getTheme(): Int = R.style.BottomSheetThemeTheme

    private fun onViewBind() {
        binding.apply {
            ivClose.setOnClickListener {
                dismiss()
            }

            tvAscendingSort.setOnClickListener {
                listener.onItemClick(Sort.ASC)
                dismiss()
            }

            tvDescendingSort.setOnClickListener {
                listener.onItemClick(Sort.DESC)
                dismiss()
            }

            tvDefaultSort.setOnClickListener {
                listener.onItemClick(Sort.DEFAULT)
                dismiss()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        dismiss()
    }

    interface OnClickedListener {
        fun onItemClick(sortType: Sort)
    }

}