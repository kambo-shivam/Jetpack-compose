package com.app.jcomposematerial

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


     fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }


    data class WellnessTask(val id: Int, val label: String, val checked: Boolean = false)


}