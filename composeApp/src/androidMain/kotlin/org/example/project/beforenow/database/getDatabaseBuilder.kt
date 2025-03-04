package org.example.project.beforenow.database

import androidx.room.Room
import android.content.Context
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<DeviceDatabase> {
    val dbFile = context.getDatabasePath("devices.db")
    return Room.databaseBuilder<DeviceDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}