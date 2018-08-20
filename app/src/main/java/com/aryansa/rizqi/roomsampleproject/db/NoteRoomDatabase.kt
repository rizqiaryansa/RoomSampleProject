package com.aryansa.rizqi.roomsampleproject.db

import android.os.AsyncTask
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.Database
import android.content.Context
import com.aryansa.rizqi.roomsampleproject.model.Note


@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "Note.db")
                        .build()
    }
}