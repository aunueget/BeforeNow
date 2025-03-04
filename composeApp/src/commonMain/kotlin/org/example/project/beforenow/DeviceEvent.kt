package org.example.project.beforenow

import org.example.project.beforenow.database.Device

sealed interface DeviceEvent {
    object SaveDevice: DeviceEvent
    data class SetMacAddress(val macAddress: String): DeviceEvent
    data class SetSsid(val ssid: String): DeviceEvent
    data class SetDeviceName(val deviceName: String): DeviceEvent
    data class SetSignalStrength(val signalStrength: String): DeviceEvent
    object ShowDeviceDialog: DeviceEvent
    object HideDeviceDialog: DeviceEvent
    data class DeleteDevice(val device: Device): DeviceEvent
}