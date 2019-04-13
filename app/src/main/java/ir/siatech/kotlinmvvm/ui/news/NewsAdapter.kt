package ir.siatech.kotlinmvvm.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ir.siatech.kotlinmvvm.BR.articleItem
import ir.siatech.kotlinmvvm.data.model.Article
import ir.siatech.kotlinmvvm.databinding.NewsRowItemBinding
import ir.siatech.kotlinmvvm.ui.base.DataBindingViewHolder

class NewsAdapter(
    private val items: MutableList<Article> = arrayListOf()
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }


    fun add(list: MutableList<Article>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(dataBinding: ViewDataBinding) : DataBindingViewHolder<Article>(dataBinding) {

        override fun onBind(t: Article): Unit = with(t) {
            dataBinding.setVariable(articleItem, t)
        }

    }
}

