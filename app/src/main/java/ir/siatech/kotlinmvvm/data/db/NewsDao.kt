package ir.siatech.kotlinmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import ir.siatech.kotlinmvvm.data.model.Article

@Dao
interface NewsDao {

    @Insert
    fun insertArticle(article: Article): Completable

    @Query("DELETE FROM article_table")
    fun deleteAllArticles(): Completable

    @Query("DELETE FROM article_table WHERE articleId = :id")
    fun deleteArticleById(id: Long): Completable

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): LiveData<List<Article>>
}