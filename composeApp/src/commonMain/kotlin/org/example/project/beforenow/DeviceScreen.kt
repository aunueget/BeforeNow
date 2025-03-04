package org.example.project.beforenow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DeviceScreen(
    state: DeviceState,
    deviceEvent: (DeviceEvent) -> Unit,
    navController: NavController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                deviceEvent(DeviceEvent.ShowDeviceDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
    ) {  _ ->
        if(state.isAddingDevice) {
            AddDeviceDialog(state = state, deviceEvent = deviceEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.devices) { device ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${device.deviceName} ${device.macAddress}",
                            fontSize = 20.sp
                        )
                        Text(
                            text = "ssid = ${device.ssid}",
                            fontSize = 12.sp
                        )
                        Text(
                            text = "rssi = ${device.signalStrength}",
                            fontSize = 12.sp
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.DurationScreen.withArgs(device.macAddress,device.id.toString(),device.deviceName,device.signalStrength,device.ssid))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Set Duration"
                        )
                    }
                    IconButton(onClick = {
                        deviceEvent(DeviceEvent.DeleteDevice(device))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete device"
                        )
                    }
                }
            }
        }
    }
}