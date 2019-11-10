package com.dc.storedataoffline.offlinecrud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "ClassModel",
    indices = [Index(value = ["classId"], unique = true)]
)
class ClassModel(
    @SerializedName("classId")
    @Expose
    @ColumnInfo(name = "classId")
    var classId: String,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "className")
    var className: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}