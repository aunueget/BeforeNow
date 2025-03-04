package org.example.project.beforenow.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant


@Entity
data class Duration (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val deviceId: Int,
    val startTime: Instant,
    val endTime: Instant
)