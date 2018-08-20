package com.aryansa.rizqi.roomsampleproject.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.aryansa.rizqi.roomsampleproject.R

class AddNoteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.aryansa.rizqi.roomsampleproject.REPLY"
    }
    private var mEdtNote: EditText? = null
    private var mBtnSave: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        initView()
    }

    private fun initView() {
        mEdtNote = findViewById(R.id.edit_note)
        mBtnSave = findViewById(R.id.button_save)

        mBtnSave?.setOnClickListener {
            val i = Intent()
            if(TextUtils.isEmpty(mEdtNote?.text.toString())) {
                setResult(Activity.RESULT_CANCELED, i)
            } else {
                val note = mEdtNote?.text.toString()
                i.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, i)
            }
            finish()
        }
    }
}
