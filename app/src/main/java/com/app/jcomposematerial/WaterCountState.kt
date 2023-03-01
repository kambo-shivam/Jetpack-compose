package com.app.jcomposematerial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaterCount(
    count: Int = 0,
    onIncrement: () -> Unit,
    onClose: (Int) -> Unit,
    onCheckChanged: (Int) -> Unit,
    list: List<MainViewModel.WellnessTask>,
) {

    LazyColumn {
        item {
            Button(onClick = onIncrement) {
                Text(text = "Add One")
            }
        }
        itemsIndexed(list) { index, item ->
            Card(
                modifier = Modifier
                    .padding(5.dp), elevation = 5.dp, shape = RoundedCornerShape(5.dp)
            ) {
                Row(modifier = Modifier.background(Color.Yellow)) {
                    if (count > 0) {
                        Text(
                            text = list[index].label,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(0.dp)
                                .padding(10.dp)
                                .weight(1f),
                            color = Color.Black,
                            fontSize = 15.sp,
                        )

                        Checkbox(
                            checked = list[index].checked,
                            onCheckedChange = { onCheckChanged(index) })

                        IconButton(onClick = { onClose(index) }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WaterCountStateFul(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var count by remember {
        mutableStateOf(0)
    }
    val list = remember {
        mutableStateListOf<MainViewModel.WellnessTask>().apply {
            addAll(viewModel.getWellnessTasks())
        }
    }


    WaterCount(count, onIncrement = {
        count++
    }, onClose = {
        list.removeAt(it)
    }, onCheckChanged = {
        list[it] =
            list[it].copy(id = list[it].id, label = list[it].label, checked = !list[it].checked)
    }, list)
}