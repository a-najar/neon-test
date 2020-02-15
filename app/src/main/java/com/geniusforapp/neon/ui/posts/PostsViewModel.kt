package com.geniusforapp.neon.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geniusforapp.neon.core.usecases.GetPostsUseCase
import com.geniusforapp.neon.core.usecases.PostsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase = GetPostsUseCase()) :
    ViewModel() {


    private val _postsLiveData: MutableLiveData<PostsResult> = MutableLiveData()


    val posts: LiveData<PostsResult> = _postsLiveData

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = getPostsUseCase.getPosts()
            viewModelScope.launch(Dispatchers.Main) { _postsLiveData.postValue(posts) }
        }
    }

}
