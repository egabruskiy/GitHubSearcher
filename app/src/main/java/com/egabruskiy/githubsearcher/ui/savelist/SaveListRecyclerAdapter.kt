package com.egabruskiy.githubsearcher.ui.savelist

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
import com.egabruskiy.githubsearcher.data.model.GitRepoModel
import com.egabruskiy.githubsearcher.data.model.RepositoryResponse
import com.egabruskiy.githubsearcher.ui.searcfragment.RecyclerViewListener

class SaveListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private  var repositoryList : List<GitRepoModel>? = null
        lateinit var mListener: RecyclerViewListener

        fun setList(list: List<GitRepoModel>){
            this.repositoryList = list
            notifyDataSetChanged()
        }



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.repositoriy_item, parent, false)
            return ViewHolder(view,mListener)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            repositoryList?.get(position)?.let { (holder as ViewHolder).bind(it) }

        }

        override fun getItemCount(): Int {
            return repositoryList?.size ?: 0

        }


        class ViewHolder(itemView: View, private val listener: RecyclerViewListener) : RecyclerView.ViewHolder(itemView){
            var imageView: ImageView = itemView.findViewById(R.id.avatar_owner_repository_item)
            var clickLayout: LinearLayout = itemView.findViewById(R.id.item_click_layout)
            var expandLayot: LinearLayout = itemView.findViewById(R.id.statistic_layout_repository_item)
            var openButton: Button = itemView.findViewById(R.id.open_btn_repository_item)

            fun bind(gitRepoModel: GitRepoModel) {
                Glide.with(itemView.context)
                    .load(gitRepoModel.avatarUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)

            }

        }
        fun setClickListener(listener: RecyclerViewListener){
            mListener = listener
        }

}