package com.aryansa.rizqi.roomsampleproject.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "note_table")
data class Note(@field:PrimaryKey
                @field:ColumnInfo(name = "note")
                val note: String)