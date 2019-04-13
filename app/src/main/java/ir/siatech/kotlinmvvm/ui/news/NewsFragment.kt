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

    private lateinit var viewModel: NewsViewModel

    //    private val viewModel: NewsViewModel  by lazy {
//    }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(NewsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            newsData.observe(this@NewsFragment, Observer {
                initView(it)
            })

            error.observe(this@NewsFragment, Observer {
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView(it: MutableList<Article>?) {
        rv_home_news.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_home_news.adapter = newsAdapter
        if (it!!.isNotEmpty()) {
            newsAdapter.clear()
            newsAdapter.add(it)
        } else {
            Toast.makeText(context, "Oops,List is empty!", android.widget.Toast.LENGTH_LONG).show()
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
