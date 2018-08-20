package com.aryansa.rizqi.roomsampleproject.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aryansa.rizqi.roomsampleproject.db.NoteDao
import com.aryansa.rizqi.roomsampleproject.viewmodel.NoteViewModel

class NoteViewModelFactory(private val dataSource: NoteDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}