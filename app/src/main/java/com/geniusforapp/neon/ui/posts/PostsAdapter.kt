package com.geniusforapp.neon.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geniusforapp.neon.R
import com.geniusforapp.neon.data.model.Post
import com.neon.load
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * @name Neon
 * Copyrights (c) 2020-02-15 Created By Ahmad Najar
 **/
class PostsAdapter : ListAdapter<Post, PostsViewHolder>(PostsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Post) {
        item.urls?.regular?.let {
            itemView.postImage.load(it) {
                placeHolder = R.drawable.placeholder
            }
        }
    }
}


class PostsCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}