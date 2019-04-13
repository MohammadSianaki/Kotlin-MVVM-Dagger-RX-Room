package ir.siatech.kotlinmvvm.data

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.siatech.kotlinmvvm.data.db.AppDatabase
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.data.model.NewsResponse
import ir.siatech.kotlinmvvm.data.network.ApiError
import ir.siatech.kotlinmvvm.data.network.CustomDisposable
import ir.siatech.kotlinmvvm.data.network.WebService
import retrofit2.Response
import javax.inject.Inject

const val TAG: String = "NewsRepository"

class NewsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val appDatabase: AppDatabase
) : NewsRepository {

    override fun fetchArticles(
        success: (MutableList<Article>?) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return webService
            .getHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(terminate)
            .subscribeWith(
                CustomDisposable<Response<NewsResponse>>(
                    {
                        success(it.body()?.articles)
                        Log.d("TEST", "${it.body()!!.totalResults}")
                    },
                    failure
                )
            )
    }

    override fun insertArticle(article: Article): Disposable {
        return appDatabase
            .newsDao()
            .insertArticle(article)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "Article added to database ,  id : ${article.articleId}")
            }
    }

    override fun getAllArticles(): LiveData<List<Article>> {
        return appDatabase.newsDao().getAllArticles()
    }

}