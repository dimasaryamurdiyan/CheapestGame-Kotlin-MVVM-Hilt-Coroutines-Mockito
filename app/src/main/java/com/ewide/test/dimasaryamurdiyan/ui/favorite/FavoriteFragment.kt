package com.ewide.test.dimasaryamurdiyan.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.dimasaryamurdiyan.databinding.FragmentFavoriteBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.ui.detail.DetailGameActivity
import com.ewide.test.dimasaryamurdiyan.ui.games.adapter.GameAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var gameAdapter: GameAdapter

    private val viewModel by viewModels<FavoriteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchViewModelCallback()
        onViewBind()
        onViewObserve()
    }

    private fun fetchViewModelCallback() {
        viewModel.getFavoriteGame()
    }

    private fun onViewObserve() {
        viewModel.apply {
            gameList.observe(viewLifecycleOwner) { game ->
                gameAdapter.submitData(game)
                binding.viewEmpty.root.visibility = if (game.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }
    }

    private fun onViewBind() {
        binding.apply {
            gameAdapter = GameAdapter(object : GameAdapter.OnClickListener{
                override fun onClickItem(item: Game) {
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


}