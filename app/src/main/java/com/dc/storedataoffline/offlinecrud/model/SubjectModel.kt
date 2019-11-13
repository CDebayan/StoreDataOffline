package com.dc.storedataoffline.offlinecrud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class SubjectModel(
    @SerializedName("subjectId")
    @Expose
    @ColumnInfo(name = "subjectId")
    var subjectId: String,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "Name")
    var Name: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}