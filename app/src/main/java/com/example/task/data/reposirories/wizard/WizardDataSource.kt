package com.example.task.data.reposirories.wizard

import com.example.task.data.models.Wizard
import kotlinx.coroutines.flow.Flow

interface WizardDataSource {

    fun getWizards(): Flow<List<Wizard>>

    suspend fun saveWizards(wizards: List<Wizard>)
}