package com.dc.storedataoffline.offlineimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.storedataoffline.R
import com.dc.storedataoffline.offlinecrud.response.ClassListResponse
import com.dc.storedataoffline.offlinecrud.retrofit.RetrofitClient
import com.dc.storedataoffline.offlinecrud.showToast
import kotlinx.android.synthetic.main.activity_image_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        getClassList()
    }


    private fun getClassList() {
        RetrofitClient.invoke().getImageList().enqueue(object : Callback<ImageListResponse> {

            override fun onResponse(
                call: Call<ImageListResponse>,
                response: Response<ImageListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.success == "1") {
                            setRecyclerView(it.imageList)
                        } else if (it.success == "0") {
                            showToast("")
                        } else {
                            showToast("")
                        }
                    }
                } else {
                    showToast("")
                }
            }
            override fun onFailure(call: Call<ImageListResponse>, t: Throwable) {
                Toast.makeText(this@ImageListActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun setRecyclerView(imageList : ArrayList<ImageModel>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ImageListAdapter(this,imageList)

    }
}
