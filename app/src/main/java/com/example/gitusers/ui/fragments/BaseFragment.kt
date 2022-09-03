package com.example.gitusers.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gitusers.adapter.GitUserAdapter
import com.example.gitusers.ui.GitViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts

@AndroidEntryPoint
open class BaseFragment : Fragment(){

    protected val gitViewModel by lazy{
        ViewModelProvider(requireActivity())[GitViewModel::class.java]
    }

    protected val userAdapter = GitUserAdapter()

    protected fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
    }

    //CHECK IF CONNECTED TO INTERNET
    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternetConnection(): Boolean {
        val connectivityManager = Contexts.getApplication(activity?.applicationContext).getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}