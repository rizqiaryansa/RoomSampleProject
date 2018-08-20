package com.aryansa.rizqi.roomsampleproject.db

import android.app.Application
import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.content.Context
import com.aryansa.rizqi.roomsampleproject.model.Note
import io.reactivex.Completable


class NoteRepository {

    private var mWordDao: NoteDao
    private var mAllWords: LiveData<MutableList<Note>>

    constructor(context: Context) {
        val db = NoteRoomDatabase.getDatabase(context)
        mWordDao = db.noteDao()
        mAllWords = mWordDao.getAllNote
    }

    fun getAllNotes(): LiveData<MutableList<Note>> {
        return mAllWords
    }

    fun delete() {
        mWordDao.deleteAll()
    }

    fun insert(note: Note) {
//        InsertAsyncTask(mWordDao).execute(note)
    }

    class InsertAsyncTask constructor(private val mAsyncTaskDao: NoteDao) :
            AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg params: Note): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}