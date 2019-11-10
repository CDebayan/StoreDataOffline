package com.dc.storedataoffline.offlinecrud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.storedataoffline.ItemClickListener
import com.dc.storedataoffline.R
import com.dc.storedataoffline.offlinecrud.adapter.MarksListAdapter
import com.dc.storedataoffline.offlinecrud.model.MarksModel
import com.dc.storedataoffline.offlinecrud.viewmodel.MarksViewModel
import kotlinx.android.synthetic.main.activity_student_details.*

class StudentDetailsActivity : AppCompatActivity() {


    lateinit var mStudentsId: String
    lateinit var mStudentsName: String
    lateinit var mStudentsRoll: String
    lateinit var mClassId: String
    lateinit var mClassName: String
    lateinit var marksViewModel: MarksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        marksViewModel = ViewModelProviders.of(this).get(MarksViewModel::class.java)


        val bundleExtra = intent.getBundleExtra("bundle")
        if(bundleExtra != null){
            mStudentsId = bundleExtra.getString("studentsId","").toString()
            mStudentsName = bundleExtra.getString("studentsName","").toString()
            mStudentsRoll = bundleExtra.getString("studentsRoll","").toString()
            mClassId = bundleExtra.getString("classId","").toString()
            mClassName = bundleExtra.getString("className","").toString()
        }


        name.text = mStudentsName
        roll.text = "Roll : ${mStudentsRoll}"
        className.text = mClassName

        if(::marksViewModel.isInitialized){
            marksViewModel.getMarksListById(mStudentsId).observe(this,object : Observer<List<MarksModel>>{
                override fun onChanged(marksList: List<MarksModel>) {
                    setRecyclerView(marksList)
                }
            })
        }

    }

    private fun setRecyclerView(marksList: List<MarksModel>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MarksListAdapter(marksList,object : ItemClickListener{
            override fun onItemClick(position: Int, operation: String) {

            }
        })
    }
}
