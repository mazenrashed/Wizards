package com.example.task.ui.wizards


data class WizardsUiState (
    val isLoading : Boolean = false,
    val wizards: List<Wizard> = emptyList(),
    val message : String? = null,
){
    data class Wizard(
        val id: String,
        val firstName: String?,
        val lastName: String?,
        val elixirs: List<Elixir>
    ){
        data class Elixir(
            val id: String,
            val name: String?
        )

    }
}