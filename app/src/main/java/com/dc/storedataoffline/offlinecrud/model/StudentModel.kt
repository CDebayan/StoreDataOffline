package com.dc.storedataoffline.offlinecrud.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "StudentModel",
    indices = [Index(value = ["studentsId"], unique = true)]
)
data class StudentModel(
    @SerializedName("studentsId")
    @Expose
    @ColumnInfo(name = "studentsId")
    var studentsId: String,

    @SerializedName("studentsName")
    @Expose
    @ColumnInfo(name = "studentsName")
    var studentsName: String,

    @SerializedName("studentsRoll")
    @Expose
    @ColumnInfo(name = "studentsRoll")
    var studentsRoll: String = "",

    @SerializedName("classId")
    @Expose
    @ColumnInfo(name = "classId")
    var classId: String

) {
    constructor(studentsId: String, studentsName: String, studentsRoll: String, classId: String, className: String) : this(
        studentsId,
        studentsName,
        studentsRoll,
        classId
    ){
        this.className = className
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Ignore
    var className : String = ""

    @SerializedName("marksList")
    @Expose
    @Ignore
    lateinit var marksList: List<MarksModel>



}