package com.reo.running.githubclient

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {
    val repositoryList : LiveData<List<Github>?> = repository.getRepository()
    // LiveDataインスタンス作れない

    companion object {
        class Factory(private val repository: MainRepository = MainRepository()): ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(repository) as T
        }
    }
}