package com.example.task.ui.wizards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.data.models.Wizard
import com.example.task.data.remote.DefaultResponse
import com.example.task.data.reposirories.elixirs.ElixirsDataSource
import com.example.task.data.reposirories.wizard.WizardDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WizardsViewModel @Inject constructor(
    private val wizardsDataSource: WizardDataSource,
) : ViewModel() {
    private val _uiState = MutableStateFlow(WizardsUiState())
    val uiState: StateFlow<WizardsUiState> = _uiState

    init {
        fetchWizards()
        observeWizards()
    }

    private fun fetchWizards() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            (wizardsDataSource.fetchWizards() as? DefaultResponse.Fail)?.let {
                _uiState.update {
                    it.copy(message = it.message)
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun observeWizards() {
        viewModelScope.launch {
            wizardsDataSource.getWizards().collect { result ->
                _uiState.update {
                    it.copy(
                        wizards = result.map {
                            WizardsUiState.Wizard(
                                id = it.id,
                                firstName = it.firstName,
                                lastName = it.lastName,
                                elixirs = it.elixirs.map {
                                    WizardsUiState.Wizard.Elixir(
                                        id = it.id,
                                        name = it.name
                                    )
                                })
                        }
                    )

                }
            }
        }
    }

    fun messageShown() {
        _uiState.update {
            it.copy(message = null)
        }
    }
}