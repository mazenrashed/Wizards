package com.example.task.ui

sealed class Screen(val route: String){
    object Wizards: Screen("wizards")
    object WizardDetails: Screen("wizard_details")
    object ElixirDetails: Screen("elixir_details")

}
