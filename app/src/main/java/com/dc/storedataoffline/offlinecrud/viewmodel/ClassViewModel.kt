package com.dc.storedataoffline.offlinecrud.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dc.storedataoffline.offlinecrud.database.AppDAO
import com.dc.storedataoffline.offlinecrud.database.AppDatabase
import com.dc.storedataoffline.offlinecrud.model.ClassModel

class ClassViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val appDAO: AppDAO
    private val callBacks: MutableLiveData<String>

    init {
        val appDB = AppDatabase.invoke(application)
        appDAO = appDB.appDao()
        callBacks = MutableLiveData()
    }

    fun getClassDetail(classId  : String) : LiveData<ClassModel>{
        return appDAO.getClassDetails(classId)
    }

    fun deleteClassTable() : MutableLiveData<String>{
        ExecuteAsyncTask(this@ClassViewModel,"deleteTable").execute(null)

        return callBacks
    }

    fun insertClassList(classList: List<ClassModel>) : LiveData<String>{
        val cb: MutableLiveData<String> = MutableLiveData()
        for (classModel in classList) {
            ExecuteAsyncTask(this@ClassViewModel,"insert").execute(classModel)
        }
        cb.value = "All Data Inserted"
        return cb
    }



    companion object {
        private class ExecuteAsyncTask(
            private val viewModel: ClassViewModel,
            private val operation: String
        ) : AsyncTask<ClassModel, Void, String>() {
            override fun doInBackground(vararg classModel: ClassModel): String {
                if(operation == "insert"){
                    viewModel.appDAO.insertClass(classModel[0])
                }else if(operation == "deleteTable"){
                    viewModel.appDAO.deleteClassTable()
                }
                return operation
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                if (result == "insert") {
                    viewModel.callBacks.value = "Class Data Inserted"
                } else if (result == "deleteTable") {
                    viewModel.callBacks.value = "Table Deleted"
                }
            }

        }
    }


}
