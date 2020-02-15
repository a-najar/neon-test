package com.geniusforapp.neon.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.geniusforapp.neon.R
import com.geniusforapp.neon.core.usecases.PostsResult
import kotlinx.android.synthetic.main.posts_fragment.*

class PostsFragment : Fragment(R.layout.posts_fragment) {

    companion object {
        fun newInstance() = PostsFragment()
    }

    private val viewModel by viewModels<PostsViewModel>()
    private val postsAdapter by lazy { PostsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(listPosts) {
            adapter = postsAdapter
        }
        viewModel.getPosts()
        viewModel.posts.observe(this, Observer { validateResult(it) })
    }

    private fun validateResult(result: PostsResult?) {
        (result as? PostsResult.Posts)?.let { postsAdapter.submitList(it.posts) }
        (result as? PostsResult.Error)?.let { it.error.printStackTrace() }
    }


}
