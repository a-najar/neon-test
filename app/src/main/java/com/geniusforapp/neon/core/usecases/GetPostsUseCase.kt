package com.geniusforapp.neon.core.usecases

import com.geniusforapp.neon.core.repositories.PostsRepositories
import com.geniusforapp.neon.core.usecases.PostsResult.*
import com.geniusforapp.neon.data.model.Post

/**
 * @name Neon
 * Copyrights (c) 2020-02-15 Created By Ahmad Najar
 **/
class GetPostsUseCase(private val postsRepositories: PostsRepositories = PostsRepositories()) {


    suspend fun getPosts(): PostsResult {
        return try {
            val list = postsRepositories.getPosts()

            validateResult(list)
        } catch (throwable: Throwable) {
            Error(throwable)
        } finally {
            Error(Throwable("error some where"))
        }
    }

    private fun validateResult(data: List<Post>): PostsResult {
        return if (!data.isNullOrEmpty()) {
            Posts(data)
        } else {
            Empty
        }
    }
}


sealed class PostsResult {
    data class Posts(val posts: List<Post>) : PostsResult()
    object Empty : PostsResult()
    data class Error(val error: Throwable) : PostsResult()
}