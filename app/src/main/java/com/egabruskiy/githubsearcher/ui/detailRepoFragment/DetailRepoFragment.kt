
package com.egabruskiy.githubsearcher.ui.detailRepoFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.data.model.ResourceResult
import com.egabruskiy.githubsearcher.databinding.DetailRepoFragmentBinding
import com.egabruskiy.githubsearcher.databinding.SearchFragmentBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.android.synthetic.main.detail_repo_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRepoFragment : Fragment() {

    private val viewBinding by viewBinding(DetailRepoFragmentBinding::bind)

    private val args: DetailRepoFragmentArgs by navArgs()

    private  val viewModel: DetailRepoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_repo_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.saveBtn.setOnClickListener {
            viewModel.saveRepo(args.model)
        }

        args.model.id?.let { checkRoomRepo(it) }
        Glide.with(this)
            .load(args.model.avatarUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(owner_avatar_detail_view)
    }


    fun checkRoomRepo(id:String){
     viewModel.getRepo(id)?.observe(viewLifecycleOwner){result->
         when (result) {
             is ResourceResult.Success -> {
                 viewBinding.saveBtn.visibility=View.GONE
                 viewBinding.removeBtn.visibility=View.VISIBLE
             }
             is ResourceResult.Empty -> {
//                 Todo   showEmpty
             }
         }
     }
    }
}