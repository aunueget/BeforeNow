package org.example.project.beforenow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform