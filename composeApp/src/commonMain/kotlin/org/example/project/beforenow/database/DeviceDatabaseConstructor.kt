package org.example.project.beforenow.database

import androidx.room.RoomDatabaseConstructor

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object xxDeviceDatabaseConstructor : RoomDatabaseConstructor<DeviceDatabase> {
    override fun initialize(): DeviceDatabase
}