package com.example.task.data.reposirories.wizard

import com.example.task.data.models.Wizard
import com.example.task.data.remote.DefaultResponse
import kotlinx.coroutines.flow.Flow

interface WizardDataSource {

    suspend fun getWizards(): Flow<List<Wizard>>

    suspend fun saveWizards(wizards: List<Wizard>)

    suspend fun fetchWizards() : DefaultResponse<List<Wizard>>

}