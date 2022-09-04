package com.example.gitusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitusers.databinding.UserRowViewBinding
import com.example.gitusers.model.CacheGitUser
import com.example.gitusers.model.GitUser
import com.example.gitusers.ui.fragments.HomeFragmentDirections
import com.squareup.picasso.Picasso

class GitUserAdapter : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            UserRowViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val differ = differ.currentList[position]
        holder.bind(differ)

        holder.itemView.setOnClickListener {
            val directionToMainFragment = HomeFragmentDirections.actionFirstFragmentToSecondFragment(differ)
            holder.itemView.findNavController().navigate(directionToMainFragment)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<CacheGitUser>() {
        override fun areItemsTheSame(oldItem: CacheGitUser, newItem: CacheGitUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CacheGitUser, newItem: CacheGitUser): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}

class MyViewHolder(
    private val binding: UserRowViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(users: CacheGitUser) {
        binding.nameTxtVw.text = users.login
        Picasso.get()
            .load(users.avatar_url)
            .fit()
            .into(binding.imgVW)
    }
}

