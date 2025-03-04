package org.example.project.beforenow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.example.project.beforenow.database.DeviceDao
import org.example.project.beforenow.database.Duration

class DurationViewModel(
    private val dao: DeviceDao
): ViewModel() {
    private val _state = MutableStateFlow(DurationState())
    private val _durations = MutableStateFlow(DurationState())
    val state = combine(_state,_durations){state,durations->
        state.copy(
            durations = (dao.getDeviceWithDurations(_state.value.device.macAddress)).firstOrNull()?.durations
                ?: emptyList()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DurationState())
    fun onEvent(event: DurationEvent){
        when(event) {
            is DurationEvent.DeleteDuration -> {
                viewModelScope.launch {
                    dao.deleteDuration(event.duration)
                    _state.update { it.copy(
                        durations = (dao.getDeviceWithDurations(state.value.device.macAddress))[0].durations
                    ) }
                }

            }
            is DurationEvent.HideDurationDialog -> {
                _state.update { it.copy(
                    isAddingDuration = false
                ) }
            }
            is DurationEvent.SaveDuration -> {
                val deviceId = state.value.device.id
                val startTime = state.value.startTime
                val endTime = state.value.endTime
                val duration = Duration(
                    id = 0,
                    deviceId = deviceId,
                    startTime = startTime,
                    endTime = endTime
                )
                viewModelScope.launch{
                    dao.insertDuration(duration);
                }
                viewModelScope.launch {
                    _state.update { it.copy(
                        durations = (dao.getDeviceWithDurations(state.value.device.macAddress))[0].durations
                    ) }
                }
                _state.update { it.copy(
                    isAddingDuration = false,
                    startTime = Clock.System.now(),
                    endTime = Clock.System.now() ,
                ) }
            }
            is DurationEvent.SetStartTime -> {
                _state.update { it.copy(
                    startTime = Instant.fromEpochMilliseconds(event.start.toLong())
                ) }
            }
            is DurationEvent.SetEndTime -> {
                _state.update { it.copy(
                    endTime = Instant.fromEpochMilliseconds(event.end.toLong())
                ) }
            }
            is DurationEvent.ShowDurationDialog -> {
                _state.update { it.copy(
                    isAddingDuration = true
                ) }
            }
            is DurationEvent.UpdateDeviceValue -> {
                _state.update { it.copy(
                    device = event.device
                )}
            }

        }
    }

}