package org.example.project.beforenow.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.datetime.Instant

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: Device)

    @Delete
    suspend fun deleteDevice( device: Device)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDuration(duration: Duration)

    @Delete
    suspend fun deleteDuration( device: Duration)

    @Transaction
    @Query("SELECT * FROM device WHERE macAddress = :macAddy")
    suspend fun getDeviceWithDurations(macAddy: String): List<DeviceWithDurations>

    @Transaction
    @Query("SELECT * FROM duration JOIN device WHERE startTime < :checkDate AND endTime > :checkDate")
    suspend fun getDurationsBetween(checkDate: Instant): List<DeviceWithDurations>

    @Transaction
    @Query("SELECT dev.*,dur.* " +
            "FROM device dev JOIN duration dur " +
            "ON dev.id = dur.deviceId WHERE :macAddy = dev.macAddress " +
            "AND dur.startTime = (SELECT MAX(startTime) FROM duration WHERE duration.deviceId=dev.id)")
    suspend fun getDeviceLastSeen(macAddy: String): List<DeviceWithDurations>

    @Transaction
    @Query("SELECT * FROM device")
    suspend fun getAllDevices(): List<Device>
}