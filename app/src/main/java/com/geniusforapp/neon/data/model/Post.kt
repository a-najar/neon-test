package com.geniusforapp.neon.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Post(
    @SerializedName("categories")
    val categories: List<Category?>? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = null,
    @SerializedName("likes")
    val likes: Int? = null,
    @SerializedName("links")
    val links: Links? = null,
    @SerializedName("urls")
    val urls: Urls? = null,
    @SerializedName("user")
    val user: User? = null,
    @SerializedName("width")
    val width: Int? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Category(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("links")
        val links: Links? = null,
        @SerializedName("photo_count")
        val photoCount: Int? = null,
        @SerializedName("title")
        val title: String? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Links(
            @SerializedName("photos")
            val photos: String? = null,
            @SerializedName("self")
            val self: String? = null
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Links(
        @SerializedName("download")
        val download: String? = null,
        @SerializedName("html")
        val html: String? = null,
        @SerializedName("self")
        val self: String? = null
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Urls(
        @SerializedName("full")
        val full: String? = null,
        @SerializedName("raw")
        val raw: String? = null,
        @SerializedName("regular")
        val regular: String? = null,
        @SerializedName("small")
        val small: String? = null,
        @SerializedName("thumb")
        val thumb: String? = null
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class User(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("links")
        val links: Links? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("profile_image")
        val profileImage: ProfileImage? = null,
        @SerializedName("username")
        val username: String? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Links(
            @SerializedName("html")
            val html: String? = null,
            @SerializedName("likes")
            val likes: String? = null,
            @SerializedName("photos")
            val photos: String? = null,
            @SerializedName("self")
            val self: String? = null
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class ProfileImage(
            @SerializedName("large")
            val large: String? = null,
            @SerializedName("medium")
            val medium: String? = null,
            @SerializedName("small")
            val small: String? = null
        ) : Parcelable
    }
}