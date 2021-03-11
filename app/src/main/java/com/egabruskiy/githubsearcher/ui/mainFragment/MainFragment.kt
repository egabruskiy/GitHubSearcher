package com.egabruskiy.githubsearcher.ui.mainFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.databinding.MainFragmentBinding
import com.egabruskiy.githubsearcher.util.SAVE_LIST_PAGE_INDEX
import com.egabruskiy.githubsearcher.util.SEARCH_LIST_PAGE_INDEX
import com.google.android.material.tabs.TabLayoutMediator
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import timber.log.Timber

import java.lang.IndexOutOfBoundsException

class MainFragment : Fragment() {

    private val viewBinding by viewBinding(MainFragmentBinding::bind)


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setTabLayout()
        setToolbar()

    }

    private fun setViewPager() {
       viewBinding.viewPager.adapter = MainPagerPagerAdapter(this)
    }


    private fun setTabLayout() {
        val viewPager = viewBinding.viewPager
        val tabLayout = viewBinding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()
    }


//    private fun getTabIcon(position: Int): Int {
//        return when (position) {
////            SEARCH_LIST_PAGE_INDEX ->
////            SAVE_LIST_PAGE_INDEX ->
//            else -> throw IndexOutOfBoundsException()
//        }
//    }


    private fun getTabTitle(position: Int): String {
        return when (position) {
            SEARCH_LIST_PAGE_INDEX -> getString(R.string.search)
            SAVE_LIST_PAGE_INDEX -> getString(R.string.saved)
            else -> throw IndexOutOfBoundsException()
        }
    }


    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
    }


}