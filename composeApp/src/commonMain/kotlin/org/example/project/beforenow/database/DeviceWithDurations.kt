package org.example.project.beforenow.database

import androidx.room.Embedded
import androidx.room.Relation

data class DeviceWithDurations (
    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "id",
        entityColumn = "deviceId"
    )
    val durations: List<Duration>
)