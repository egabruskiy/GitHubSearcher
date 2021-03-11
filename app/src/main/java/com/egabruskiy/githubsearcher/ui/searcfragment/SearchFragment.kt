package com.egabruskiy.githubsearcher.ui.searcfragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.databinding.SearchFragmentBinding
import com.egabruskiy.githubsearcher.ui.login.GoogleSignInFragment
import com.egabruskiy.githubsearcher.ui.mainFragment.MainFragment
import com.egabruskiy.githubsearcher.ui.mainFragment.MainFragmentDirections
import com.egabruskiy.githubsearcher.util.Util
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {



    private val recyclerAdapter = SearchFragmentRecyclerAdapter()
    private  val viewModel: SearchViewModel by viewModel()
    private val viewBinding by viewBinding(SearchFragmentBinding::bind)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.images.observe(viewLifecycleOwner) { result ->

            setAdapter()
            when (result){
                is ResourceResult.Loading -> {

                }
                is ResourceResult.Success -> {
                    recyclerAdapter.setList(result.data)
                }

                is ResourceResult.Empty -> {


                }

                is ResourceResult.Error -> {

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
                    viewModel.setQuery(newText)
                    return true
                }
            })
        }
    }


    private fun setAdapter(){
        viewBinding.searchFragmentRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter

        }

        recyclerAdapter.setClickListener(object :
                RecyclerViewListener {
            override fun onClick(response: RepositoryResponse) {

                val direction = MainFragmentDirections.mainFragmentToDetailFragment(Util.getRepoModelFromResponse(response))
                findNavController().navigate(direction)
            }
        })

    }

}