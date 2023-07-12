package com.ewide.test.dimasaryamurdiyan.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.dimasaryamurdiyan.R
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.databinding.ActivityDetailGameBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.ui.base.BaseActivity
import com.ewide.test.dimasaryamurdiyan.ui.detail.adapter.DealsAdapter
import com.ewide.test.dimasaryamurdiyan.utils.loadImage
import com.ewide.test.dimasaryamurdiyan.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class DetailGameActivity: BaseActivity(){
    private lateinit var binding: ActivityDetailGameBinding
    private lateinit var dealsAdapter: DealsAdapter

    private val viewModel by viewModels<DetailGameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        fetchDetailGame(detailGame?.gameID)
        onViewBind(detailGame)
        onViewObserve()
    }

    private fun onViewBind(detailGame: Game?) {
        binding.apply {
            dealsAdapter = DealsAdapter(object : DealsAdapter.OnClickListener{
                override fun onClickItem(item: DetailGame.Deal) {

                }
            })
            with(rvGames){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = dealsAdapter
            }

           detailGame?.let { game ->
               var statusFavorite = detailGame.isFavorite
               setStatusFavorite(statusFavorite)
               fab.setOnClickListener {
                   statusFavorite = !statusFavorite
                   viewModel.setFavoriteGame(game, statusFavorite)
                   setStatusFavorite(statusFavorite)
               }
           }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red_24dp))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_24dp))
        }
    }

    private fun onViewObserve() {
        viewModel.apply {
            detailGame.observe(this@DetailGameActivity) { detailGame ->
                when(detailGame) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        setupView(detailGame.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        this@DetailGameActivity.shortToast(detailGame.message.toString())
                    }
                }

            }
        }
    }

    private fun setupView(data: DetailGame?) {
        binding.apply {
            ivImage.loadImage(url = data?.info?.thumb ?: "")
            tvName.text = data?.info?.title
            tvPrice.text = data?.cheapestPriceEver?.price
            dealsAdapter.submitData(data?.deals as List<DetailGame.Deal>?)
        }


    }


    private fun fetchDetailGame(gameID: String?) {
        viewModel.getDetailGame(gameID?.toInt() ?: 0)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}