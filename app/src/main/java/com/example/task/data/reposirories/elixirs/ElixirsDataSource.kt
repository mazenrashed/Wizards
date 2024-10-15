package com.example.task.data.reposirories.elixirs

import com.example.task.data.models.Elixir
import com.example.task.data.remote.DefaultResponse

interface ElixirsDataSource {
    suspend fun getElixirById(id: String): DefaultResponse<Elixir>
}