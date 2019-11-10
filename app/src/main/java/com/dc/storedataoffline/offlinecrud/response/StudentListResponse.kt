package com.dc.storedataoffline.offlinecrud.response

import com.dc.storedataoffline.offlinecrud.model.StudentModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StudentListResponse(
    @SerializedName("success")
    @Expose
    val success: String,

    @SerializedName("studentList")
    @Expose
    val studentList: List<StudentModel>
)
