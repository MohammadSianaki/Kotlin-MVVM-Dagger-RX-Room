package ir.siatech.kotlinmvvm.data

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function8
import io.reactivex.schedulers.Schedulers
import ir.siatech.kotlinmvvm.data.db.AppDatabase
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.data.model.NewsResponse
import ir.siatech.kotlinmvvm.data.network.ApiError
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
        return Observable.zip(
            webService.getNYTimesHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getBloomergHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getCNNHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getEconomistHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getIndependentHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getRTHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getWSJHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            webService.getTechCrunchHeadlines().subscribeOn(Schedulers.io()).toObservable(),
            Function8<
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    Response<NewsResponse>,
                    List<Article>> { t1,
                                     t2,
                                     t3,
                                     t4,
                                     t5,
                                     t6,
                                     t7,
                                     t8 ->
                return@Function8 t1.body()?.articles.orEmpty() +
                        t2.body()?.articles.orEmpty() +
                        t3.body()?.articles.orEmpty() +
                        t4.body()?.articles.orEmpty() +
                        t5.body()?.articles.orEmpty() +
                        t6.body()?.articles.orEmpty() +
                        t7.body()?.articles.orEmpty() +
                        t8.body()?.articles.orEmpty()
            }
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                success(it.toMutableList())
            }
    }

    override fun insertArticle(article: Article): Disposable {
        return appDatabase
            .newsDao()
            .insertArticle(article)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "Article added to database ,  id : ${article.description}")
            }
    }

    override fun getAllCachedArticles(): LiveData<List<Article>> {
        return appDatabase.newsDao().getAllArticles()
    }

}