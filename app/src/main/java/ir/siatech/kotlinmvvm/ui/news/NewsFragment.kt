package ir.siatech.kotlinmvvm.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.databinding.NewsFragmentBinding
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

class NewsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(NewsViewModel::class.java)
    }

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rv_home_news.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_home_news.adapter = newsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            newsData.observe(this@NewsFragment, Observer {
                refreshItems(it)
            })

            error.observe(this@NewsFragment, Observer {
                ll_news_progress.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun refreshItems(it: MutableList<Article>?) {
        ll_news_progress.visibility = View.GONE
        it?.run {
            newsAdapter.add(it)
        }
    }

    companion object {
        @JvmStatic
        val TAG = NewsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = NewsFragment().apply {
            arguments = Bundle()
        }
    }
}
