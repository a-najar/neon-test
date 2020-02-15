package com.neon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.collection.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream


/**
 * @name Neon
 * Copyrights (c) 2020-02-06 Created By Ahmad Najar
 **/


fun ImageView.load(imageUrl: String, neonBuilder: (NeonBuilder.() -> Unit)? = null) {
    var neon = neonBuilder?.run { NeonBuilder().apply(this) }
    if (neon == null) {
        neon = NeonBuilder()
    }
    neon.placeHolder?.let { setImageResource(it) }
    CoroutineScope(Dispatchers.IO).launch {
        try {
            loadImage(imageUrl)?.let {
                GlobalScope.launch(Dispatchers.Main) { animateBitmap(it) }
            }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch { neon.placeHolder?.let { setImageResource(it) } }
        }
    }
}


class NeonBuilder(
    var placeHolder: Int? = null,
    var animIn: Int = android.R.anim.fade_in,
    var animOut: Int = android.R.anim.fade_out
)

private fun loadImage(imageUrl: String): Bitmap? {
    return (getBitmapFromMemCache(imageUrl) ?: loadBitmapFromUrl(imageUrl)?.apply {
        addBitmapToMemoryCache(imageUrl, this)
    })
}

@Throws(Exception::class)
fun loadBitmapFromUrl(url: String): Bitmap? {
    Log.d("image:network", url)
    return Request.Builder()
        .url(url)
        .build().run { client.newCall(this).execute().body?.byteStream()?.toBitmap() }
}


private fun loadAnimation(context: Context, anim: Int): Animation =
    AnimationUtils.loadAnimation(context, anim)

fun ImageView.animateBitmap(
    bitmap: Bitmap?,
    animIn: Animation = loadAnimation(context, android.R.anim.fade_in),
    animOut: Animation = loadAnimation(context, android.R.anim.fade_out)
) {
    animOut.setAnimationListener(object : ImageAnimationListener() {
        override fun onImageAnimationEnd(animation: Animation) {
            setImageBitmap(bitmap)
            startAnimation(animIn)
        }
    })
    startAnimation(animOut)
}

private val client = OkHttpClient()
private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
private val cacheSize = maxMemory / 8
private val imageLoaderCash = ImageLoaderCash()
fun InputStream.toBitmap(): Bitmap = BitmapFactory.decodeStream(this)


private class ImageLoaderCash : LruCache<String, Bitmap>(cacheSize) {
    override fun sizeOf(key: String, bitmap: Bitmap): Int {
        return bitmap.byteCount / 1024
    }
}

private abstract class ImageAnimationListener : AnimationListener {
    override fun onAnimationRepeat(animation: Animation) {}

    override fun onAnimationEnd(animation: Animation) {
        onImageAnimationEnd(animation)
    }

    override fun onAnimationStart(animation: Animation) {}

    abstract fun onImageAnimationEnd(animation: Animation)
}


private fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
    if (getBitmapFromMemCache(key) == null) {
        imageLoaderCash.put(key, bitmap)
    }
}

private fun getBitmapFromMemCache(key: String): Bitmap? = imageLoaderCash.get(key)
