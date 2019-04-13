package ir.siatech.kotlinmvvm.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.siatech.kotlinmvvm.data.NewsRepository
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.data.network.ApiError
import ir.siatech.kotlinmvvm.ui.base.BaseViewModel
import javax.inject.Inject

const val TAG = "NewsViewModel"

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel() {
    val newsData: MutableLiveData<MutableList<Article>> by lazy { MutableLiveData<MutableList<Article>>() }
    val error: MutableLiveData<ApiError>  by lazy { MutableLiveData<ApiError>() }

    init {
        Log.d(TAG, "init block")
        fetchAllArticles()
    }

    fun getArticlesList(): LiveData<List<Article>> {
        return newsRepository.getAllCachedArticles()
    }

    private fun fetchAllArticles() {
        Log.d(TAG, "fetchAllArticles() called")
        newsRepository.fetchArticles(
            { articles ->
                Log.d(TAG, "getNewsData.success() called with size : ${articles?.size}")
                newsData.postValue(articles)
                if (!articles.isNullOrEmpty()) {
                    for (article in articles) {
                        Log.d(TAG, "<<<< saving article : $article")
                        newsRepository.insertArticle(article).also { compositeDisposable.add(it) }
                    }
                }
            }, {
                Log.d(TAG, "getNewsData.error() called with: $it")
                error.value = it
            }, {
                Log.d(TAG, "getNewsData.terminate() called")
            }
        ).also { compositeDisposable.add(it) }
    }

}
