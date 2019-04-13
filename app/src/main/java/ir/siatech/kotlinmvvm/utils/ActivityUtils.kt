package ir.siatech.kotlinmvvm.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this).get(viewModelClass)

class ActivityUtils {

    companion object {
        fun addFragmentToActivity(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            frameId: Int,
            addToBackStack: Boolean,
            tag: String
        ) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            if (addToBackStack) transaction.addToBackStack(tag)
            transaction.commit()
        }


        fun replaceFragmentInActivity(
            fragmentManager: FragmentManager?,
            fragment: Fragment,
            frameId: Int,
            addToBackStack: Boolean,
            tag: String?
        ) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(frameId, fragment, tag)
            if (addToBackStack) transaction.addToBackStack(tag)
            transaction.commit()
        }
    }


}