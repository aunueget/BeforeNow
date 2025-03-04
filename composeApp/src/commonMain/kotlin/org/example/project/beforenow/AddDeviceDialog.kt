package org.example.project.beforenow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddDeviceDialog(
    state: DeviceState,
    deviceEvent: (DeviceEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            deviceEvent(DeviceEvent.HideDeviceDialog)
        },
        title = { Text(text = "Add device") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.deviceName,
                    onValueChange = {
                        deviceEvent(DeviceEvent.SetDeviceName(it))
                    },
                    placeholder = {
                        Text(text = "Device name")
                    }
                )
                TextField(
                    value = state.macAddress,
                    onValueChange = {
                        deviceEvent(DeviceEvent.SetMacAddress(it))
                    },
                    placeholder = {
                        Text(text = "Mac Address")
                    }
                )
                TextField(
                    value = state.ssid,
                    onValueChange = {
                        deviceEvent(DeviceEvent.SetSsid(it))
                    },
                    placeholder = {
                        Text(text = "ssid name")
                    }
                )
                TextField(
                    value = state.signalStrength,
                    onValueChange = {
                        deviceEvent(DeviceEvent.SetSignalStrength(it))
                    },
                    placeholder = {
                        Text(text = "Signal Strength")
                    }
                )

            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    deviceEvent(DeviceEvent.SaveDevice)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}