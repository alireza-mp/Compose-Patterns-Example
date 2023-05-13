@file:OptIn(ExperimentalMaterialApi::class)

package com.example.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostItem(
    title: String,
    onItemClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        onClick = onItemClick,
    ) {
        Text(title)
    }


}