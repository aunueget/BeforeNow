package org.example.project.beforenow

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.beforenow.database.getDeviceDatabase
import org.example.project.beforenow.database.getDatabaseBuilder

fun main() = application {
    val dbBuilder = getDatabaseBuilder()

    Window(
        onCloseRequest = ::exitApplication,
        title = "BeforeNow",
    ) {
        Navigation(dbBuilder)
    }
}
