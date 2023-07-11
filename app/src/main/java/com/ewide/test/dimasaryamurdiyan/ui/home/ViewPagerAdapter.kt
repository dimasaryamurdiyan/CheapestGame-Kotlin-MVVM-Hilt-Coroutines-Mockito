package com.ewide.test.dimasaryamurdiyan.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ewide.test.dimasaryamurdiyan.ui.favorite.FavoriteFragment
import com.ewide.test.dimasaryamurdiyan.ui.games.GamesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return GamesFragment()
            1 -> return FavoriteFragment()
            else -> GamesFragment()
        }
    }

    companion object {
        private const val NUM_TABS = 2
    }
}