package com.ewide.test.dimasaryamurdiyan.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.databinding.ItemDealsBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.utils.Constants
import com.ewide.test.dimasaryamurdiyan.utils.roundTo

class DealsAdapter(private val itemClick: OnClickListener): RecyclerView.Adapter<DealsAdapter.ViewHolder>()  {

    private val diffCallback = object : DiffUtil.ItemCallback<DetailGame.Deal>() {
        override fun areItemsTheSame(
            oldItem: DetailGame.Deal,
            newItem: DetailGame.Deal
        ): Boolean {
            return oldItem.dealID == newItem.dealID
        }

        override fun areContentsTheSame(
            oldItem: DetailGame.Deal,
            newItem: DetailGame.Deal
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<DetailGame.Deal>?) = differ.submitList(value)

    inner class ViewHolder(private val binding: ItemDealsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailGame.Deal){
            binding.apply {
                val savings = item.savings?.toDouble()?.roundTo(2)

                tvStoreName.text = Constants.productName[item.storeID?.toInt()]
                tvDiscountPrecentage.apply {
                    visibility = if (savings == 0.00) View.GONE else View.VISIBLE
                    text = savings.toString()
                    setBackgroundResource(R.drawable.bg_orange_rounded_4dp)
                }
                tvNormalPrice.apply {
                    visibility = if (savings == 0.00) View.GONE else View.VISIBLE
                    text = item.retailPrice
                }
                tvDiscountPrice.text = item.price

                binding.root.setOnClickListener {
                    itemClick.onClickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DealsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemDealsBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: DealsAdapter.ViewHolder,
        position: Int
    ) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface OnClickListener {
        fun onClickItem(item: DetailGame.Deal)
    }
}