package com.example.task.data.remote

import com.example.task.data.models.Elixir
import com.example.task.data.models.Wizard
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEndPoints {

    @GET("Wizards")
    suspend fun getWizards(): Response<List<Wizard>>



    @GET("Elixirs/{id}")
    suspend fun saveStaffRating(
        @Path("id") id: String,
    ): Response<Elixir>


}