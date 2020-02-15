package com.geniusforapp.neon.core.repositories

import com.geniusforapp.neon.data.model.Post
import com.geniusforapp.neon.data.repositories.PostsRepositoryImplementation

/**
 * @name Neon
 * Copyrights (c) 2020-02-15 Created By Ahmad Najar
 **/


@Suppress("FunctionName")
fun PostsRepositories(): PostsRepositories = PostsRepositoryImplementation()


interface PostsRepositories {

    suspend fun getPosts(): List<Post>
}