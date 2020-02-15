package com.geniusforapp.neon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geniusforapp.neon.R
import com.geniusforapp.neon.ui.posts.PostsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PostsFragment.newInstance())
            .commit()

    }
}
