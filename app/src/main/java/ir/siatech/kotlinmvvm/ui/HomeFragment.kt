package ir.siatech.kotlinmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import ir.siatech.kotlinmvvm.R
import ir.siatech.kotlinmvvm.ui.news.NewsFragment
import ir.siatech.kotlinmvvm.utils.ActivityUtils
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    //-------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_nav.setOnNavigationItemSelectedListener { showFragment(it) }
    }

    private fun showFragment(it: MenuItem): Boolean {
        return when (it.itemId) {
            R.id.bottom_nav_rss_item -> {
                loadFragment(
                    fragmentManager?.findFragmentByTag(NewsFragment.TAG) ?: NewsFragment.newInstance()
                )
                true
            }

            else -> false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        ActivityUtils
            .replaceFragmentInActivity(
                fragmentManager,
                fragment,
                R.id.fl_home_content,
                false,
                null
            )
    }

    companion object {
        @JvmStatic
        val TAG: String = HomeFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle()
            }
    }
}
