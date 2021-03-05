package com.reo.running.githubclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class MainRepository(private val remoteDataSource: RemoteDataSource = RemoteDataSource()) {
     fun getRepository() :LiveData<List<Github>?> {
        return liveData {
            val repos = remoteDataSource.getRepository()
            emit(repos) // LiveData返却 emitした時にデータ入る
            kotlinx.coroutines.delay(3000)
            emit(listOf<Github>())
        }
    }
}