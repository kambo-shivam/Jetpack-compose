package com.app.jcomposematerial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SetExpandedView() {
    LazyColumn(modifier = Modifier.padding(20.dp)) {
        items(30) {
            val expanded = remember {
                mutableStateOf(false)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = if (expanded.value) 40.dp else 0.dp)
                    .background(
                        Color.LightGray
                    )
            ) {
                Text(
                    text = "Hello Guys,",
                    Modifier
                        .weight(1f)

                )
                ElevatedButton(onClick = {
                    expanded.value = !expanded.value
                }) {
                    if (expanded.value)
                        Text(text = "Show less")
                    else
                        Text(text = "Show more")
                }
            }
        }
    }
}


