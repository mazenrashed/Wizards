package com.example.task.ui.wizards

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.task.data.models.Elixir
import com.example.task.data.models.Wizard
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardsScreen(
    navHostController: NavHostController,
    viewModel: WizardsViewModel = hiltViewModel(),
) {

    val uiState: WizardsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState.message) {
        if (uiState.message != null) {
            Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
            viewModel.messageShown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Task")
                },
            )
        }
    ) { innerPadding ->
        WizardList(modifier = Modifier.padding(innerPadding), wizards = uiState.wizards)
    }
}

@Composable
fun WizardList(modifier: Modifier = Modifier, wizards: List<WizardsUiState.Wizard>) {
    LazyColumn(modifier) {
        items(wizards) { wizard ->
            Column {
                Text(
                    text = (wizard.firstName ?: "") + (wizard.lastName ?: ""),
                    style = MaterialTheme.typography.titleLarge
                )
                ElixirList(elixirs = wizard.elixirs)
            }

        }
    }
}

@Composable
fun ElixirList(elixirs: List<WizardsUiState.Wizard.Elixir>) {
    Column {
        elixirs.forEach { Text(text = it.name ?: "") }
    }
}

@Preview(showBackground = true)
@Composable
fun WizardListPreview() {
    val wizard = WizardsUiState.Wizard(
        id = "d986fd0c-daa7-4d6c-bb93-83034ec32193",
        firstName = "Joi",
        lastName = "Baruffio",
        elixirs = listOf(
            WizardsUiState.Wizard.Elixir(
                id = "afe48e27-d5de-4a14-9975-65a5b45aadaa",
                name = "Baruffio's Brain Elixir"
            )
        ),
    )
    WizardList(wizards = listOf(wizard))
}