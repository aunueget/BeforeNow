package org.example.project.beforenow.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Device (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val deviceName : String,
    val signalStrength : String,
    val ssid : String,
    val macAddress : String
)