package org.example.project.beforenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.project.beforenow.database.getDatabaseBuilder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbBuilder = getDatabaseBuilder(applicationContext)
        setContent {
            Navigation(dbBuilder)
        }
    }
}
