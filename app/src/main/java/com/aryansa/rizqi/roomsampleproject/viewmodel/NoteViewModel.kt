package com.aryansa.rizqi.roomsampleproject.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.aryansa.rizqi.roomsampleproject.db.NoteDao
import com.aryansa.rizqi.roomsampleproject.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable


class NoteViewModel(private val dataSource: NoteDao) : ViewModel() {

    private var mAllWords: LiveData<MutableList<Note>> = dataSource.getAllNote

    fun getAllNotes(): LiveData<MutableList<Note>> {
        return mAllWords
    }

    fun insert(note: Note): Completable {
        return Completable.fromAction {
            dataSource.insert(note)
        }
    }

    fun delete() {
        dataSource.deleteAll()
    }
}