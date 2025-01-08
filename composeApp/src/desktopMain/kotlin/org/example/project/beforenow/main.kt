package org.example.project.beforenow

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BeforeNow",
    ) {
        App()
    }
}