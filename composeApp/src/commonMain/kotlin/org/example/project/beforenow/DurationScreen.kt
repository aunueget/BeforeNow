package org.example.project.beforenow

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.beforenow.database.Device
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DurationScreen(
    state: DurationState,
    durationEvent: (DurationEvent) -> Unit,
    macAddy: String,
    deviceid: String,
    deviceName: String,
    signalStrength: String,
    ssid: String

) {
    fun getTimeFormatted(theTime: Instant):String {
        return theTime.toLocalDateTime(TimeZone.UTC).toString()
    }
    durationEvent(DurationEvent.UpdateDeviceValue(
        Device(
            deviceid.toInt(),
            deviceName,
            signalStrength,
            ssid,
            macAddy
        )
    ))
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                durationEvent(DurationEvent.ShowDurationDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add duration"
                )
            }
        },
    ) { _ ->
        if(state.isAddingDuration) {
            AddDurationDialog(state = state, durationEvent = durationEvent)
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Device Name: ${deviceName}",
                            fontSize = 24.sp
                        )
                    }
                }
            }
            items(state.durations) { duration ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = getTimeFormatted(duration.startTime)+"\n" +
                                    getTimeFormatted(duration.endTime),
                            fontSize = 20.sp
                        )
                    }
                    IconButton(onClick = {
                        durationEvent(DurationEvent.DeleteDuration(duration))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete duration"
                        )
                    }
                }
            }
        }
    }
}