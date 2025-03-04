package org.example.project.beforenow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.RoomDatabase
import org.example.project.beforenow.database.DeviceDao
import org.example.project.beforenow.database.DeviceDatabase
import org.example.project.beforenow.database.getDeviceDatabase
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Navigation(
    dbBuilder: RoomDatabase.Builder<DeviceDatabase>
){
    val deviceDao = getDeviceDatabase(dbBuilder).getDao()
    val deviceViewModel = viewModel<DeviceViewModel>{ DeviceViewModel( deviceDao) }
    val durationViewModel = viewModel<DurationViewModel>{ DurationViewModel(deviceDao) }
    val deviceState by deviceViewModel.state.collectAsState()
    val durationState by durationViewModel.state.collectAsState()
    val navController = rememberNavController()

    NavHost(navController = navController,startDestination = Screen.DeviceScreen.route){
        composable(route = Screen.DeviceScreen.route){
            DeviceScreen(
                state = deviceState,
                deviceEvent = deviceViewModel::onEvent,
                navController = navController
            )
        }
        composable(
            route = Screen.DurationScreen.route + "/{macAddy}/{id}/{deviceName}/{signal}/{ssid}",
            arguments = listOf(
                navArgument("macAddy") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){ entry ->
                DurationScreen(
                    state = durationState,
                    durationEvent = durationViewModel::onEvent,
                    macAddy = entry.arguments?.getString("macAddy").toString(),
                    deviceid = entry.arguments?.getString("id").toString(),
                    deviceName = entry.arguments?.getString("deviceName").toString(),
                    signalStrength = entry.arguments?.getString("signal").toString(),
                    ssid = entry.arguments?.getString("ssid").toString()
                )
        }
    }
}
