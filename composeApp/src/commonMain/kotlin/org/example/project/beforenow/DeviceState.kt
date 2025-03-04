package org.example.project.beforenow

import org.example.project.beforenow.database.Device

data class DeviceState(
    val devices: List<Device> = emptyList(),
    val macAddress: String = "",
    val ssid: String = "",
    val signalStrength: String = "",
    val deviceName: String = "",
    val isAddingDevice: Boolean = false
)
