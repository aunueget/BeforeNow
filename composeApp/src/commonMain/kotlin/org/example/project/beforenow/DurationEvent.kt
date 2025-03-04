package org.example.project.beforenow

import org.example.project.beforenow.database.Device
import org.example.project.beforenow.database.Duration


sealed interface DurationEvent {
    object SaveDuration: DurationEvent
    data class SetStartTime(val start: String): DurationEvent
    data class SetEndTime(val end: String): DurationEvent
    object ShowDurationDialog: DurationEvent
    object HideDurationDialog: DurationEvent
    data class DeleteDuration (val duration: Duration): DurationEvent
    data class UpdateDeviceValue(val device: Device): DurationEvent
}