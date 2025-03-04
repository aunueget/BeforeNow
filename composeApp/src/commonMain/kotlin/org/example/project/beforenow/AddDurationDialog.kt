package org.example.project.beforenow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock

@Composable
fun AddDurationDialog(
    state: DurationState,
    durationEvent: (DurationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTime = Clock.System.now()
    var start by remember { mutableStateOf(currentTime.toEpochMilliseconds().toString()) }
    var end by remember { mutableStateOf(currentTime.toEpochMilliseconds().toString()) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            durationEvent(DurationEvent.HideDurationDialog)
        },
        title = {
            Text(text = "Duration for: ${state.device.deviceName}") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Starttime")
                TextField(
                    value = start,
                    onValueChange = {
                        start = it
                        durationEvent(DurationEvent.SetStartTime(it))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(text = "Endtime")
                TextField(
                    value = end,
                    onValueChange = {
                        end = it
                        durationEvent(DurationEvent.SetEndTime(it))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    durationEvent(DurationEvent.SaveDuration)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}