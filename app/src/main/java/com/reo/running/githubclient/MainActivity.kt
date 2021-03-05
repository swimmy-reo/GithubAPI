package com.reo.running.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.githubclient.databinding.ActivityMainBinding
import com.reo.running.githubclient.databinding.ItemMainRecyclervewBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Companion.Factory()
    }
    private val mainRecyclervewAdapter: MainListAdapter by lazy {
        MainListAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.mainRecyclerView.apply {
            adapter = mainRecyclervewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModel.repositoryList.observe(this) {
            mainRecyclervewAdapter.notifyDataSetChanged()
        }
    }

    private inner class MainListAdapter: RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(
            ItemMainRecyclervewBinding.inflate(LayoutInflater.from(this@MainActivity),parent,false))

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.binding.let {
                it.github = viewModel.repositoryList.value?.get(position)
                it.lifecycleOwner = this@MainActivity
            }
        }

        override fun getItemCount(): Int = viewModel.repositoryList.value?.size?: 0
    }

    private inner class MainViewHolder(val binding: ItemMainRecyclervewBinding): RecyclerView.ViewHolder(binding.root)
}