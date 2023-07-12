package com.ewide.test.dimasaryamurdiyan.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.databinding.FragmentGamesBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.ui.base.BaseFragment
import com.ewide.test.dimasaryamurdiyan.ui.detail.DetailGameActivity
import com.ewide.test.dimasaryamurdiyan.ui.games.adapter.GameAdapter
import com.ewide.test.dimasaryamurdiyan.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GamesFragment : BaseFragment() {
    private lateinit var binding: FragmentGamesBinding
    private lateinit var gameAdapter: GameAdapter

    private val viewModel by viewModels<GameViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewBind()
        onViewObserve()
    }

    private fun onViewObserve() {
        viewModel.apply {
            gameList.observe(viewLifecycleOwner) { game ->
                when(game) {
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        gameAdapter.submitData(game.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        requireContext().shortToast(game.message.toString())
                    }
                }
            }
        }
    }

    private fun onViewBind() {
        binding.apply {
            etSearch.doAfterTextChanged { text ->
                val data = text.toString()
                lifecycleScope.launch {
                    delay(SEARCH_DEBOUNCE_DURATION)
                    if(data.isNotEmpty()) {
                        viewModel.searchGame(data)
                    }
                }
            }

            gameAdapter = GameAdapter(object : GameAdapter.OnClickListener{
                override fun onClickItem(item: Game) {
                    binding.etSearch.setText("")
                    //navigate to detail screen
                    val intent = Intent(activity, DetailGameActivity::class.java)
                    intent.putExtra(DetailGameActivity.EXTRA_DATA, item)
                    startActivity(intent)
                }
            })
            with(rvGames){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }

    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.setText("")
    }

    companion object{
        const val SEARCH_DEBOUNCE_DURATION = 300L
    }

}