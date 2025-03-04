package org.example.project.beforenow.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters

@Database(
    entities = [Device::class, Duration::class],
    version = 1
)
@TypeConverters(DateConverter::class)
@ConstructedBy(DeviceDatabaseConstructor::class)
abstract class DeviceDatabase : RoomDatabase(){
    abstract fun getDao(): DeviceDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DeviceDatabaseConstructor : RoomDatabaseConstructor<DeviceDatabase> {
    override fun initialize(): DeviceDatabase
}

