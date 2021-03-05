package com.reo.running.githubclient

import java.lang.Exception

class RemoteDataSource {
    suspend fun getRepository(): List<Github>? {
        try {
          val data = APIClient.retrofit.fetchRepository() //APIを叩く
          if (data.isSuccessful) {
              return data.body() //List
          }
            else {
                throw Exception()
            }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}