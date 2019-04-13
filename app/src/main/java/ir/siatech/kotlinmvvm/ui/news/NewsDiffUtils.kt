package ir.siatech.kotlinmvvm.ui.news

import androidx.recyclerview.widget.DiffUtil
import ir.siatech.kotlinmvvm.data.model.Article

class NewsDiffUtils(
    private val oldArticles: List<Article>,
    private val newArticles: List<Article>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticles[oldItemPosition].description == newArticles[newItemPosition].description
    }

    override fun getOldListSize() = oldArticles.size

    override fun getNewListSize() = newArticles.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArticles[oldItemPosition].content == newArticles[newItemPosition].content
    }
}