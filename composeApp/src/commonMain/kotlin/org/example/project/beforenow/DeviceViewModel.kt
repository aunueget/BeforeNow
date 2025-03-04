package org.example.project.beforenow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.project.beforenow.database.Device
import org.example.project.beforenow.database.DeviceDao

class DeviceViewModel(
    private val dao: DeviceDao
): ViewModel() {
    private val _state = MutableStateFlow(DeviceState())
    private val _devices = MutableStateFlow(DeviceState())
    val state = combine(_state,_devices){state,devices->
        state.copy(
            devices = dao.getAllDevices()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DeviceState())
    fun onEvent(event: DeviceEvent){
        when(event) {
            is DeviceEvent.DeleteDevice -> {
                viewModelScope.launch {
                    dao.deleteDevice(event.device)
                    _state.update { it.copy(
                        devices = dao.getAllDevices()
                    ) }
                }

            }
            is DeviceEvent.HideDeviceDialog -> {
                _state.update { it.copy(
                    isAddingDevice = false
                ) }
            }
            is DeviceEvent.SaveDevice -> {
                val deviceName = state.value.deviceName
                val ssid = state.value.ssid
                val signalStrength = state.value.signalStrength
                val macAddress = state.value.macAddress
                if(deviceName.isBlank()||ssid.isBlank()||macAddress.isBlank()){
                    return
                }
                val device = Device(
                    id = 0,
                    deviceName = deviceName,
                    signalStrength = signalStrength,
                    ssid = ssid,
                    macAddress = macAddress
                )
                viewModelScope.launch{
                    dao.insertDevice(device)
                }
                viewModelScope.launch {
                    _state.update { it.copy(
                        devices = dao.getAllDevices()
                    ) }
                }
                _state.update { it.copy(
                    isAddingDevice = false,
                    deviceName = "",
                    ssid = "",
                    signalStrength = "",
                    macAddress = ""
                ) }
            }
            is DeviceEvent.SetDeviceName -> {
                _state.update { it.copy(
                    deviceName = event.deviceName
                ) }
            }
            is DeviceEvent.SetMacAddress -> {
                _state.update { it.copy(
                    macAddress = event.macAddress
                ) }
            }
            is DeviceEvent.SetSignalStrength -> {
            _state.update { it.copy(
                signalStrength = event.signalStrength
            ) }
        }
            is DeviceEvent.SetSsid -> {
                _state.update { it.copy(
                    ssid = event.ssid
                ) }
            }
            is DeviceEvent.ShowDeviceDialog -> {
                _state.update { it.copy(
                    isAddingDevice = true
                ) }
            }
        }
    }
}