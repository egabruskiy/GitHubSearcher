package com.egabruskiy.githubsearcher.ui.searcfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egabruskiy.githubsearcher.R
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse

class SearchFragmentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mExpandedPosition = -1

    private  var repositoryList : List<RepositoryResponse>? = null
    lateinit var mListener: RecyclerViewListener

    fun setList(imageList: List<RepositoryResponse>){
        this.repositoryList = imageList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.repositoriy_item, parent, false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isExpanded = position == mExpandedPosition

        repositoryList?.get(position)?.let { (holder as ViewHolder).bind(it) }
        (holder as ViewHolder).expandLayot.visibility = if (isExpanded) View.VISIBLE else View.GONE
//        if(isExpanded){
//            (holder as ViewHolder).expandLayot.visibility = View.VISIBLE
//        }else {
//            (holder as ViewHolder).expandLayot.visibility = View.GONE
//        }

        holder.clickLayout.setOnClickListener{
            mExpandedPosition = if (isExpanded) -1 else position
//            TransitionManager.beginDelayedTransition(rv)

            notifyDataSetChanged()

        }

    }

    override fun getItemCount(): Int {
        return repositoryList?.size ?: 0

    }


    class ViewHolder(itemView: View, private val listener: RecyclerViewListener) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.avatar_owner_repository_item)
        var clickLayout:LinearLayout = itemView.findViewById(R.id.item_click_layout)
        var expandLayot:LinearLayout = itemView.findViewById(R.id.statistic_layout_repository_item)
        var openButton:Button = itemView.findViewById(R.id.open_btn_repository_item)

        fun bind(repositoryResponse: RepositoryResponse) {
            Glide.with(itemView.context)
                .load(repositoryResponse.owner?.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)


            openButton.setOnClickListener{
                listener.onClick(repositoryResponse)
            }
        }

    }
    fun setClickListener(listener: RecyclerViewListener){
        mListener = listener
    }
}