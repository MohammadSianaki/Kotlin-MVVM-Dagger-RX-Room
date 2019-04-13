package ir.siatech.kotlinmvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
@Entity(tableName = "article_table")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val articleId: Long,

    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")

    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
) {
    override fun toString(): String {
        return "Article(articleId=$articleId, description='$description', title='$title')"
    }
}


