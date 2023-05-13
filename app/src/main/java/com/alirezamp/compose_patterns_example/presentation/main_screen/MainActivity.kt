package com.alirezamp.compose_patterns_example.presentation.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alirezamp.compose_patterns_example.ui.theme.ComposePatternsExampleTheme
import com.example.postlist.PostListRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePatternsExampleTheme {
                PostListRoute()
            }
        }
    }
}