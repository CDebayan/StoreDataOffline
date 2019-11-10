package com.dc.storedataoffline.offlinecrud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "SubjectStudentRelationModel")
data class SubjectStudentRelationModel(
    @SerializedName("studentId")
    @Expose
    @ColumnInfo(name = "studentId")
    val studentId: String,

    @SerializedName("marks")
    @Expose
    @ColumnInfo(name = "marks")
    val marks: String,

    @SerializedName("subjectId")
    @Expose
    @ColumnInfo(name = "subjectId")
    val subjectId: String
){
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    lateinit var id: String
}