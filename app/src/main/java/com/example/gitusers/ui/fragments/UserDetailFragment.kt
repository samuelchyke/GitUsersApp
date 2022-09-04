package com.example.gitusers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.gitusers.databinding.FragmentSecondBinding
import com.squareup.picasso.Picasso

class UserDetailFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSecondBinding.inflate(layoutInflater)
    }

    private val args by navArgs<UserDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.nameTextVw.text = args.currentUser.login
        Picasso.get().load(args.currentUser.avatar_url).fit().into(binding.bannerImgVw)
        return binding.root

    }

}