package com.dc.storedataoffline.offlinecrud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MarksModel")
class MarksModel(
    @SerializedName("studentId")
    @Expose
    @ColumnInfo(name = "studentId")
    var studentId: String,

    @SerializedName("marks")
    @Expose
    @ColumnInfo(name = "marks")
    var marks: String,

    @SerializedName("subjectId")
    @Expose
    @ColumnInfo(name = "subjectId")
    var subjectId: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}