package com.geniusforapp.neon.data.repositories

import com.geniusforapp.neon.BuildConfig
import com.geniusforapp.neon.core.repositories.PostsRepositories
import com.geniusforapp.neon.data.model.Post
import com.neon.get

/**
 * @name Neon
 * Copyrights (c) 2020-02-15 Created By Ahmad Najar
 **/
class PostsRepositoryImplementation : PostsRepositories {

    override suspend fun getPosts(): List<Post> = get(BuildConfig.BASE_URL)
}