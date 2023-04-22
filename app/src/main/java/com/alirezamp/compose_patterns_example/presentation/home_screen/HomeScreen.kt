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
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {

    val viewModel = getViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) {

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.data.isNotEmpty() -> {
                RenderPostList(
                    viewModel = viewModel,
                    errorMessage = state.error,
                    data = state.data,
                )
            }
            state.error != null -> {
                ErrorView(viewModel = viewModel, errorMessage = state.error)
            }
        }
    }
}

@Composable
private fun RenderPostList(
    viewModel: HomeViewModel,
    errorMessage: String?,
    data: List<HomeScreenViewDataModel>,
) {
    LazyColumn {
        item {
            errorMessage?.let {
                ErrorView(viewModel = viewModel, errorMessage)
            }
        }
        itemsIndexed(items = data) { _, item ->
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

@Composable
private fun ErrorView(viewModel: HomeViewModel, errorMessage: String?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = errorMessage ?: "")
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Button(onClick = viewModel::onButtonClick) {
            Text(text = "tap to try again!")
        }
    }
}
