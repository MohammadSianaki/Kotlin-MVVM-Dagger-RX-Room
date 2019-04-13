package ir.siatech.kotlinmvvm.data.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
data class NewsResponse(
    @SerializedName("articles")
    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)



