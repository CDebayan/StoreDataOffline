package com.dc.storedataoffline.offlinecrud.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dc.storedataoffline.offlinecrud.database.AppDAO
import com.dc.storedataoffline.offlinecrud.database.AppDatabase
import com.dc.storedataoffline.offlinecrud.model.MarksModel
import com.dc.storedataoffline.offlinecrud.model.StudentModel

class MarksViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val appDAO: AppDAO
    private val callBacks: MutableLiveData<String>

    init {
        val appDB = AppDatabase.invoke(application)
        appDAO = appDB.appDao()
        callBacks = MutableLiveData()
    }


    fun insertMarks(marksModel: MarksModel): MutableLiveData<String> {
        ExecuteAsyncTask(
            this@MarksViewModel,
            "insert"
        ).execute(marksModel)
        return callBacks
    }

    fun deleteMarksTable(): MutableLiveData<String> {
        ExecuteAsyncTask(
            this@MarksViewModel,
            "deleteTable"
        ).execute(null)
        return callBacks
    }

    fun getMarksListById(studentId : String) : LiveData<List<MarksModel>>{
        return appDAO.getMarksListById(studentId)
    }


    companion object {
        private class ExecuteAsyncTask(
            private val viewModel: MarksViewModel,
            private val operation: String
        ) : AsyncTask<MarksModel, Void, String>() {
            override fun doInBackground(vararg marksModel: MarksModel): String {
                if (operation == "insert") {
                    viewModel.appDAO.insertMark(marksModel[0])
                }else if(operation == "deleteTable"){
                    viewModel.appDAO.deleteMarksTable()
                }
                return operation
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                if(result == "insert"){
                    viewModel.callBacks.value = "Marks Data Inserted"
                }else if(result == "deleteTable"){
                    viewModel.callBacks.value = "Table Deleted"
                }
            }

        }
    }


}
