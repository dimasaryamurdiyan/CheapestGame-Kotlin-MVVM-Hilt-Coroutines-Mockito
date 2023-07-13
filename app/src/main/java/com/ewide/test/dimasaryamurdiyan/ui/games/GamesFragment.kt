package com.ewide.test.dimasaryamurdiyan.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.data.source.local.preference.IPreference
import com.ewide.test.dimasaryamurdiyan.databinding.FragmentGamesBinding
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.ui.base.BaseFragment
import com.ewide.test.dimasaryamurdiyan.ui.bottomsheet.BottomSheetDialogSort
import com.ewide.test.dimasaryamurdiyan.ui.detail.DetailGameActivity
import com.ewide.test.dimasaryamurdiyan.ui.games.adapter.GameAdapter
import com.ewide.test.dimasaryamurdiyan.utils.DelayedTextWatcher
import com.ewide.test.dimasaryamurdiyan.utils.Sort
import com.ewide.test.dimasaryamurdiyan.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GamesFragment : BaseFragment(), BottomSheetDialogSort.OnClickedListener {
    private lateinit var binding: FragmentGamesBinding
    private lateinit var gameAdapter: GameAdapter

    private val viewModel by viewModels<GameViewModel>()

    @Inject
    lateinit var preference: IPreference

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

            checkSortType()

            etSearch.addTextChangedListener(
                DelayedTextWatcher(
                    SEARCH_DEBOUNCE_DURATION, ::performSearch
                )
            )

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
                setHasFixedSize(false)
                adapter = gameAdapter
            }

            fab.setOnClickListener {
                BottomSheetDialogSort.newInstance(
                    this@GamesFragment
                ).show(childFragmentManager, BottomSheetDialogSort.TAG)
            }
        }

    }

    private fun checkSortType() {
        when(preference.getSortType()){
            Sort.DEFAULT.toString() -> {
                viewModel.sortedASC()
            }
            Sort.ASC.toString() -> {
                viewModel.sortedASC()
            }
            Sort.DESC.toString() -> {
                viewModel.sortedDESC()
            }
        }
    }

    private fun performSearch(s: String) {
        viewModel.searchGame(s)
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.setText("")
    }

    override fun onItemClick(sortType: Sort) {
       when(sortType) {
           Sort.ASC -> {
               viewModel.sortedASC()
               preference.setSortType(Sort.ASC.toString())
           }
           Sort.DESC -> {
               viewModel.sortedDESC()
               preference.setSortType(Sort.DESC.toString())
           }
           Sort.DEFAULT -> {
               viewModel.sortedASC()
               preference.setSortType(Sort.DEFAULT.toString())
           }
       }
    }

    companion object{
        const val SEARCH_DEBOUNCE_DURATION = 300L
    }

}