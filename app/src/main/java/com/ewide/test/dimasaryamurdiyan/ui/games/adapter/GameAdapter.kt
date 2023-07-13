package com.ewide.test.dimasaryamurdiyan.ui.games.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.databinding.ItemGamesBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.utils.loadImage

class GameAdapter(private val itemClick: OnClickListener): RecyclerView.Adapter<GameAdapter.ViewHolder>()  {

    private val diffCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(
            oldItem: Game,
            newItem: Game
        ): Boolean {
            return oldItem.gameID == newItem.gameID
        }

        override fun areContentsTheSame(
            oldItem: Game,
            newItem: Game
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<Game>?) = differ.submitList(value)

    inner class ViewHolder(private val binding: ItemGamesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Game){
            binding.apply {
                ivProfile.loadImage(
                    url = item.thumb ?: "",
                )
                tvName.text = item.external
                tvPrice.text = root.context.getString(R.string.price, item.cheapest)

                binding.root.setOnClickListener {
                    itemClick.onClickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemGamesBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: GameAdapter.ViewHolder,
        position: Int
    ) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface OnClickListener {
        fun onClickItem(item: Game)
    }
}