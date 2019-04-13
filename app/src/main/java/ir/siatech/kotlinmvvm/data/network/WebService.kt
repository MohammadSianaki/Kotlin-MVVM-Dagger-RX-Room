package ir.siatech.kotlinmvvm.data.network

import io.reactivex.Single
import ir.siatech.kotlinmvvm.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("top-headlines?sources=techcrunch")
    fun getTechCrunchHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=bbc-news")
    fun getBBCHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=bloomberg")
    fun getBloomergHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=associated-press")
    fun getAPHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=cnn")
    fun getCNNHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=independent")
    fun getIndependentHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=the-economist")
    fun getEconomistHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=the-new-york-times")
    fun getNYTimesHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=the-wall-street-journal")
    fun getWSJHeadlines(): Single<Response<NewsResponse>>

    @GET("top-headlines?sources=rt")
    fun getRTHeadlines(): Single<Response<NewsResponse>>
}