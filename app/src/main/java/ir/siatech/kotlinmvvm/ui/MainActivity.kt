package ir.siatech.kotlinmvvm.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ir.siatech.kotlinmvvm.R
import ir.siatech.kotlinmvvm.ui.news.NewsFragment
import ir.siatech.kotlinmvvm.utils.ActivityUtils

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNewsFragment()
    }

    private fun setupNewsFragment() {
        supportFragmentManager.findFragmentById(R.id.fl_main_content) ?: ActivityUtils.replaceFragmentInActivity(
            supportFragmentManager,
            NewsFragment.newInstance(),
            R.id.fl_main_content,
            false,
            NewsFragment.TAG
        )

    }


}
