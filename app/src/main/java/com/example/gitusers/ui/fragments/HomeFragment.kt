package com.example.gitusers.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitusers.databinding.FragmentFirstBinding
import com.example.gitusers.model.mapToCache
import com.example.gitusers.utils.NetworkResult
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }

    private fun initRecyclerView() {
        //Recycler View
        binding.userRecVw.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initRecyclerView()
        if (hasInternetConnection()){
            observeData()
        }else{
            setRecyclerViewUsingDB()
        }


        return binding.root
    }

    private fun observeData() {
        gitViewModel.users.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    userAdapter.differ.submitList(response.data?.mapToCache())
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

    private fun setRecyclerViewUsingDB() = lifecycleScope.launch{
        gitViewModel.getUsersFromDB().observe(viewLifecycleOwner){
            userAdapter.differ.submitList(it)
        }
    }

}

