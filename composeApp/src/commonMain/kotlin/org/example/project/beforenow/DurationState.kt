package org.example.project.beforenow

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.example.project.beforenow.database.Device
import org.example.project.beforenow.database.Duration

data class DurationState(
    val durations: List<Duration> = emptyList(),
    val device: Device = Device(-1, "", "", "", ""),
    val startTime: Instant = Clock.System.now(),
    val endTime: Instant = Clock.System.now(),
    val isAddingDuration: Boolean = false
)