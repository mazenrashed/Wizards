package com.example.task.data.reposirories.elixirs

import com.example.task.data.models.Elixir

interface ElixirsDataSource {
    fun getElixirById(id: Int): Elixir?
}