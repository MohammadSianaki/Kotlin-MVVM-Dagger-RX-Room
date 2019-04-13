package ir.siatech.kotlinmvvm.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import dagger.android.support.DaggerAppCompatActivity
import ir.siatech.kotlinmvvm.R
import ir.siatech.kotlinmvvm.utils.ActivityUtils

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupHomeFragment()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.app_name)
    }

    private fun setupHomeFragment() {
        supportFragmentManager.findFragmentById(R.id.fl_main_content) ?: ActivityUtils.replaceFragmentInActivity(
            supportFragmentManager,
            HomeFragment.newInstance(),
            R.id.fl_main_content,
            false,
            HomeFragment.TAG
        )

    }


}
