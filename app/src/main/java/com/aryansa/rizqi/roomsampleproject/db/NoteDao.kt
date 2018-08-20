package com.aryansa.rizqi.roomsampleproject.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.aryansa.rizqi.roomsampleproject.model.Note

@Dao
interface NoteDao {

    @get:Query("SELECT * from note_table ORDER BY note DESC")
    val getAllNote: LiveData<MutableList<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()
}