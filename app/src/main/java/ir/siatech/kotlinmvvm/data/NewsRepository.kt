package ir.siatech.kotlinmvvm.data

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.data.network.ApiError

interface NewsRepository {
    fun fetchArticles(
        success: (MutableList<Article>?) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun insertArticle(article: Article): Disposable

    fun getAllCachedArticles(): LiveData<List<Article>>
}