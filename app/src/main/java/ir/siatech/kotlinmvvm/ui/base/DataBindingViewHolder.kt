package ir.siatech.kotlinmvvm.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingViewHolder<T>(val dataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    constructor(
        @NonNull inflater: LayoutInflater, @IdRes layoutId: Int,
        @NonNull parent: ViewGroup, @NonNull attachToParent: Boolean
    ) : this(DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, attachToParent))

    abstract fun onBind(t: T)
}