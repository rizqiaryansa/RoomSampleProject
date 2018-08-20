package com.aryansa.rizqi.roomsampleproject.injection

import android.content.Context
import com.aryansa.rizqi.roomsampleproject.db.NoteDao
import com.aryansa.rizqi.roomsampleproject.db.NoteRoomDatabase

object DatabaseModule {
    fun provideNoteDataSource(context: Context): NoteDao {
        val database = NoteRoomDatabase.getDatabase(context)
        return database.noteDao()
    }

    fun provideViewModelFactory(context: Context): NoteViewModelFactory {
        val dataSource = provideNoteDataSource(context)
        return NoteViewModelFactory(dataSource)
    }
}