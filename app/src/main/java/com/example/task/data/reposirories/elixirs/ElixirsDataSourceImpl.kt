package com.example.task.data.reposirories.elixirs

import com.example.task.data.models.Elixir
import com.example.task.data.remote.ApiEndPoints
import com.example.task.data.remote.DefaultResponse
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElixirsDataSourceImpl @Inject constructor(private val apiEndPoints: ApiEndPoints) : ElixirsDataSource {
    override suspend fun getElixirById(id: String): DefaultResponse<Elixir> {
        return try {
            DefaultResponse.create(apiEndPoints.getElixirById(id))
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }

}