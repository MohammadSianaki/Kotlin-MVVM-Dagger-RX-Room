package ir.siatech.kotlinmvvm.data.network

import io.reactivex.Single
import ir.siatech.kotlinmvvm.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("top-headlines?sources=techcrunch")
    fun getHeadlines(): Single<Response<NewsResponse>>
}