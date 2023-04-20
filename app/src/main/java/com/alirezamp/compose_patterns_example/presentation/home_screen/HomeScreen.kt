package com.alirezamp.compose_patterns_example.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alirezamp.compose_patterns_example.utils.UiState

@Composable
fun HomeScreen() {

    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {

        when (val currentState = uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                RenderScreen(
                    viewModel = viewModel,
                    homeScreenUiModel = currentState.data,
                    errorMessage = null
                )
            }
            is UiState.Error -> {
                RenderScreen(
                    viewModel = viewModel,
                    homeScreenUiModel = currentState.data,
                    errorMessage = currentState.message,
                )
            }
        }
    }
}

@Composable
private fun RenderScreen(
    viewModel: HomeViewModel,
    homeScreenUiModel: HomeScreenUiModel,
    errorMessage: String?,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            errorMessage?.let {
                ErrorView(viewModel = viewModel, message = errorMessage)
            }
        }
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = viewModel::onButtonClick) {
                    Text(text = "press me!")
                }
            }
        }
        itemsIndexed(items = homeScreenUiModel.posts) { _, item ->
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = item.title,
            )
        }
    }
}

@Composable
private fun ErrorView(viewModel: HomeViewModel, message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Button(onClick = viewModel::onButtonClick) {
            Text(text = "tap to try again!")
        }
    }
}
