package ir.siatech.kotlinmvvm.ui

import ir.siatech.kotlinmvvm.data.NewsRepository
import ir.siatech.kotlinmvvm.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(newsRepository: NewsRepository) : BaseViewModel() {

}