package com.egabruskiy.githubsearcher.ui.savelist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.databinding.DetailRepoFragmentBinding
import com.egabruskiy.githubsearcher.databinding.SaveListFragmentBinding
import com.egabruskiy.githubsearcher.ui.mainFragment.MainFragmentDirections
import com.egabruskiy.githubsearcher.ui.searcfragment.RecyclerViewListener
import com.egabruskiy.githubsearcher.ui.searcfragment.SearchFragmentRecyclerAdapter
import com.egabruskiy.githubsearcher.util.Util
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveListFragment : Fragment() {

    private val recyclerAdapter = SaveListRecyclerAdapter()

    private  val viewModel: SaveListViewModel by viewModel()
    private val viewBinding by viewBinding(SaveListFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        observeRepositories()
        return inflater.inflate(R.layout.save_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }


    private fun observeRepositories(){
        viewModel.savedRepositories.observe(viewLifecycleOwner){result->
            when (result) {
                is ResourceResult.Success -> {
                    recyclerAdapter.setList(result.data)
                }
                is ResourceResult.Empty -> {
//                 Todo   showEmpty
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        setSearchView(menu)
    }


    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)

        (searchItem.actionView as SearchView).apply {
            // Set hint text
            queryHint = getString(R.string.search_hint)

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    //Todo search
//                    viewModel.setQuery(newText)
                    return true
                }
            })
        }
    }


    private fun setAdapter(){
        viewBinding.saveListFragmentRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter

        }

        recyclerAdapter.setClickListener(object :
            RecyclerViewListener {
            override fun onClick(response: RepositoryResponse) {

            }
        })

    }
}