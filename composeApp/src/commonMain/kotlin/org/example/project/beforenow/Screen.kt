package org.example.project.beforenow

sealed class Screen(val route: String) {
    object DeviceScreen : Screen("device_screen")
    object DurationScreen : Screen("duration_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}