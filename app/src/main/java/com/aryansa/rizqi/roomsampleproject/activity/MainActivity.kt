package com.aryansa.rizqi.roomsampleproject.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.aryansa.rizqi.roomsampleproject.R
import com.aryansa.rizqi.roomsampleproject.R.string.empty_not_saved
import com.aryansa.rizqi.roomsampleproject.adapter.NoteListAdapter
import com.aryansa.rizqi.roomsampleproject.injection.DatabaseModule
import com.aryansa.rizqi.roomsampleproject.injection.NoteViewModelFactory
import com.aryansa.rizqi.roomsampleproject.model.Note
import com.aryansa.rizqi.roomsampleproject.viewmodel.NoteViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var mNoteViewModel: NoteViewModel
    private lateinit var viewModelFactory: NoteViewModelFactory
    private lateinit var adapter: NoteListAdapter
    private val disposable = CompositeDisposable()

    companion object {
        private var ADD_NOTE_NEW_ACTIVITY: Int = 1
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        adapter = NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModelFactory = DatabaseModule.provideViewModelFactory(this)
        mNoteViewModel = ViewModelProviders.of(this, viewModelFactory).
                get(NoteViewModel::class.java)

        mNoteViewModel.getAllNotes().observe(this, Observer {
            adapter.setNotes(it!!)
        })

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val i = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(i, ADD_NOTE_NEW_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ADD_NOTE_NEW_ACTIVITY && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                val addNote = Note(data.getStringExtra(AddNoteActivity.EXTRA_REPLY))

                disposable.add(mNoteViewModel.insert(addNote).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({}, { error -> Log.e(TAG, "unable to save Note",
                                error)}))

                Toast.makeText(this, "Notes saved successfull",
                        Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, empty_not_saved, Toast.LENGTH_SHORT).show()
        }
    }
}
