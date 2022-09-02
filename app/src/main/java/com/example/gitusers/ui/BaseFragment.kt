package com.example.gitusers.ui

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment(){

    protected val gitViewModel by lazy{
        ViewModelProvider(requireActivity())[GitViewModel::class.java]
    }

    protected fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
    }

}