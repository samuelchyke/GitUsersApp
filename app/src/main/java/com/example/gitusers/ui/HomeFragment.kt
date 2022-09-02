package com.example.gitusers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gitusers.R
import com.example.gitusers.databinding.FragmentFirstBinding
import com.example.gitusers.utils.NetworkResult

class HomeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        observeData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun observeData() {
        gitViewModel.users.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                }
                is NetworkResult.Error -> {
                    response.message?.let { message ->
                        showError(message)
                    }
                }
                is NetworkResult.Loading -> {
//                    showProgressBar()
                }
                else -> {}
            }
        }
        gitViewModel.getUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}