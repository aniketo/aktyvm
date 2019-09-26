package com.aniket.videosly.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aniket.videosly.fragments.HomeFragment
import com.aniket.videosly.fragments.MoviesFragment
import com.aniket.videosly.fragments.ShowsFragment


class SectionsPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return MoviesFragment()
            2 -> return ShowsFragment()
            else -> return HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}
