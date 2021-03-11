package com.egabruskiy.githubsearcher.ui.mainFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egabruskiy.githubsearcher.ui.savelist.SaveListFragment
import com.egabruskiy.githubsearcher.ui.searcfragment.SearchFragment
import com.egabruskiy.githubsearcher.util.SAVE_LIST_PAGE_INDEX
import com.egabruskiy.githubsearcher.util.SEARCH_LIST_PAGE_INDEX

class MainPagerPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val fragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        SEARCH_LIST_PAGE_INDEX to { SearchFragment() },
        SAVE_LIST_PAGE_INDEX to { SaveListFragment() }
    )

    override fun getItemCount() = fragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}

