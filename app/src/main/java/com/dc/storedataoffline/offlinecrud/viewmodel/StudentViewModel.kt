package com.dc.storedataoffline.offlinecrud.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dc.storedataoffline.offlinecrud.database.AppDAO
import com.dc.storedataoffline.offlinecrud.database.AppDatabase
import com.dc.storedataoffline.offlinecrud.model.StudentModel

class StudentViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val appDAO: AppDAO
    private val callBacks: MutableLiveData<String>

    init {
        val appDB = AppDatabase.invoke(application)
        appDAO = appDB.appDao()
        callBacks = MutableLiveData()
    }


    fun insertStudent(studentModel: StudentModel): MutableLiveData<String> {
        ExecuteAsyncTask(
            this@StudentViewModel,
            "insert"
        ).execute(studentModel)
        return callBacks
    }

    fun     deleteStudentTable(): MutableLiveData<String> {
        ExecuteAsyncTask(
            this@StudentViewModel,
            "deleteTable"
        ).execute(null)
        return callBacks
    }

    fun getStudentList() : LiveData<List<StudentModel>>{
        return appDAO.getStudentList()
    }


    companion object {
        private class ExecuteAsyncTask(
            private val viewModel: StudentViewModel,
            private val operation: String
        ) : AsyncTask<StudentModel, Void, String>() {
            override fun doInBackground(vararg studentModel: StudentModel): String {
                if (operation == "insert") {
                    viewModel.appDAO.insertStudent(studentModel[0])
                } else if (operation == "deleteTable") {
                    viewModel.appDAO.deleteStudentTable()
                }
                return operation
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                if (result == "insert") {
                    viewModel.callBacks.value = "Student Data Inserted"
                } else if (result == "deleteTable") {
                    viewModel.callBacks.value = "Table Deleted"
                }
            }

        }
    }


}
