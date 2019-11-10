package com.dc.storedataoffline.offlinecrud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dc.storedataoffline.ItemClickListener
import com.dc.storedataoffline.offlinecrud.adapter.StudentListAdapter
import com.dc.storedataoffline.offlinecrud.model.ClassModel
import com.dc.storedataoffline.offlinecrud.model.StudentModel
import com.dc.storedataoffline.offlinecrud.response.ClassListResponse
import com.dc.storedataoffline.offlinecrud.response.StudentListResponse
import com.dc.storedataoffline.offlinecrud.retrofit.RetrofitClient
import com.dc.storedataoffline.offlinecrud.viewmodel.ClassViewModel
import com.dc.storedataoffline.offlinecrud.viewmodel.MarksViewModel
import com.dc.storedataoffline.offlinecrud.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.activity_offline_crud_activiy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class OfflineCrudActivity : AppCompatActivity() {

    //private lateinit var studentList: List<StudentModel>
    lateinit var studentViewModel: StudentViewModel
    lateinit var marksViewModel: MarksViewModel
    lateinit var classViewModel: ClassViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.dc.storedataoffline.R.layout.activity_offline_crud_activiy)

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        marksViewModel = ViewModelProviders.of(this).get(MarksViewModel::class.java)
        classViewModel = ViewModelProviders.of(this).get(ClassViewModel::class.java)

        fetchStudentList()

        deleteAllTables().observe(this, Observer { del ->
            Log.d("debayan","cla")
            if (del[0] && del[1] && del[2]) {

                getClassList()
            }
        })

        onClickListener()
    }

    private fun onClickListener() {
//        recyclerView.addOnItemTouchListener(
//            RecyclerItemClickListener(
//                this,
//                recyclerView,
//                object : RecyclerItemClickListener.OnItemClickListener {
//                    override fun onItemClick(view: View, position: Int, motionEvent: MotionEvent) {
//
//                    }
//                    override fun onLongItemClick(view: View?, position: Int) {}
//                })
//        )
    }

    private fun showElapsedTime(s: String) {
        val date = Date()
        //Pattern for showing milliseconds in the time "SSS"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val stringDate = sdf.format(date)
        Log.d(s, stringDate)
    }

    private fun deleteAllTables(): MutableLiveData<Array<Boolean>> {
        val deleted = arrayOf(false, false, false)
        val cb: MutableLiveData<Array<Boolean>> = MutableLiveData()
        studentViewModel.deleteStudentTable().observe(this, Observer { value ->
            if (value == "Table Deleted") {
                deleted[0] = true
                cb.value = deleted
            }

        })
        marksViewModel.deleteMarksTable().observe(this, Observer { value ->
            if (value == "Table Deleted") {
                deleted[1] = true
                cb.value = deleted
            }
        })
        classViewModel.deleteClassTable().observe(this, Observer { value ->
            if (value == "Table Deleted") {
                deleted[2] = true
                cb.value = deleted
            }
        })
        return cb
    }

    private fun getStudentList() {
        RetrofitClient.invoke().getStudentList().enqueue(object : Callback<StudentListResponse> {

            override fun onResponse(
                call: Call<StudentListResponse>,
                response: Response<StudentListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.success == "1") {
                            saveStudentDataToLocalDB(it.studentList)
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
            override fun onFailure(call: Call<StudentListResponse>, t: Throwable) {
                Toast.makeText(this@OfflineCrudActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getClassList() {
        RetrofitClient.invoke().getClassList().enqueue(object : Callback<ClassListResponse> {

            override fun onResponse(
                call: Call<ClassListResponse>,
                response: Response<ClassListResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.success == "1") {
                            saveClassDataToLocalDB(it.classList)
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
            override fun onFailure(call: Call<ClassListResponse>, t: Throwable) {
                Toast.makeText(this@OfflineCrudActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveClassDataToLocalDB(classList: List<ClassModel>) {
        if (::classViewModel.isInitialized) {
            if (classList.isNotEmpty()) {
                classViewModel.insertClassList(classList).observe(this, Observer { message ->
                    getStudentList()
                })

            }
        }
    }

    private fun saveStudentDataToLocalDB(studentList: List<StudentModel>) {
        if (::studentViewModel.isInitialized) {
            if (studentList.isNotEmpty()) {
                for (studentModel in studentList) {
                    studentViewModel.insertStudent(studentModel)
                    if (studentModel.marksList.isNotEmpty()) {
                        for (markModel in studentModel.marksList) {
                            marksViewModel.insertMarks(markModel)
                        }
                    }
                }
            }
        }

    }

    private fun fetchStudentList() {
        if (::studentViewModel.isInitialized) {
            studentViewModel.getStudentList()
                .observe(this, Observer<List<StudentModel>> { studentList ->
                    setRecyclerView(studentList)
                })
        }
    }

    private fun setRecyclerView(studentList: List<StudentModel>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val studentListAdapter = StudentListAdapter(studentList,object : ItemClickListener{
            override fun onItemClick(position: Int, operation: String) {
                val studentModel = studentList[position]
                goToNextActivity(studentModel)
            }
        })
        recyclerView.adapter = studentListAdapter

        if ( studentList.isNotEmpty()) {
            for (i in studentList.indices) {
                if (::classViewModel.isInitialized) {
                    classViewModel.getClassDetail(studentList[i].classId).observe(
                        this,
                        Observer<ClassModel> { data ->
                            if (data != null) {
                                studentList[i].className = data.className
                                studentListAdapter.notifyItemChanged(i)
                                //studentListAdapter.notifyDataSetChanged()
                            }
                        }
                    )
                }
            }
        }

    }

    private fun goToNextActivity(studentModel: StudentModel) {
        val bundle = Bundle()
        bundle.putString("studentsId",studentModel.studentsId)
        bundle.putString("studentsName",studentModel.studentsName)
        bundle.putString("studentsRoll",studentModel.studentsRoll)
        bundle.putString("classId",studentModel.classId)
        bundle.putString("className",studentModel.className)

        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("bundle",bundle)
        startActivity(intent)

    }
}
