package com.dc.storedataoffline.offlinecrud.response

import com.dc.storedataoffline.offlinecrud.model.ClassModel
import com.dc.storedataoffline.offlinecrud.model.StudentModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassListResponse(
    @SerializedName("success")
    @Expose
    val success: String,

    @SerializedName("classList")
    @Expose
    val classList: List<ClassModel>
)
