package org.example.project.beforenow.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<DeviceDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "devices.db")
    return Room.databaseBuilder<DeviceDatabase>(
        name = dbFile.absolutePath,
    )

}
