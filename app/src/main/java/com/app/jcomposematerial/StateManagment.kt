package com.app.jcomposematerial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(onContinueClicked: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Welcome!!")
            Button(onClick = onContinueClicked) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by remember {
        mutableStateOf(true)
    }
    if (shouldShowOnBoarding) {
        SplashScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        SetExpandedView()
    }
}