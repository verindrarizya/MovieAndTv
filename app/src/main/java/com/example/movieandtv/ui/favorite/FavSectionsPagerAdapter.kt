package com.example.movieandtv.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movieandtv.R
import com.example.movieandtv.ui.favorite.favmovielist.FavMovieListFragment
import com.example.movieandtv.ui.favorite.favtvshowlist.FavTvShowListFragment

class FavSectionsPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie_tab_title, R.string.tv_show_tab_title)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavMovieListFragment()
            1 -> FavTvShowListFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

}