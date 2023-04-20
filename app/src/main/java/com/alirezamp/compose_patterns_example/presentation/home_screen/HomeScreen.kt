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

@Composable
fun HomeScreen() {

    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {

        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.data.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.padding(it)) {
                    item {
                        state.error?.let {
                            ErrorView(viewModel = viewModel, state = state)
                        }
                    }
                    itemsIndexed(items = state.data) { _, item ->
                        when (item) {
                            is HomeScreenViewDataModel.PressMeButton -> {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Button(onClick = viewModel::onButtonClick) {
                                        Text(text = "press me!")
                                    }
                                }
                            }
                            is HomeScreenViewDataModel.PostItem -> {
                                Text(
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    text = item.title,
                                )
                            }
                        }
                    }
                }
            }
            state.error != null -> {
                ErrorView(viewModel = viewModel, state = state)
            }
        }
    }
}

@Composable
private fun ErrorView(viewModel: HomeViewModel, state: HomeScreenState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = state.error ?: "")
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Button(onClick = viewModel::onButtonClick) {
            Text(text = "tap to try again!")
        }
    }
}
