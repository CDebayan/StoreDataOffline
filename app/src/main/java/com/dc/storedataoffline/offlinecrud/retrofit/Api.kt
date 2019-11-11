package com.dc.storedataoffline.offlinecrud.retrofit


import com.dc.storedataoffline.offlinecrud.response.ClassListResponse
import com.dc.storedataoffline.offlinecrud.response.StudentListResponse
import com.dc.storedataoffline.offlineimage.ImageListResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("getStudentList.php")
    fun getStudentList() : Call<StudentListResponse>

    @GET("getClassList.php")
    fun getClassList() : Call<ClassListResponse>

    @GET("n9ihm")
    fun getImageList() : Call<ImageListResponse>
}