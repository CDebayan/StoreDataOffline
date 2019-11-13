package com.dc.storedataoffline.offlinecrud.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dc.storedataoffline.offlinecrud.database.AppDAO
import com.dc.storedataoffline.offlinecrud.database.AppDatabase
import com.dc.storedataoffline.offlinecrud.model.StudentModel


class NewStudentViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val appDB: AppDatabase = AppDatabase.invoke(application)
    private val appDAO: AppDAO
    private val callBacks: MutableLiveData<String>

    init {
        appDAO = appDB.appDao()
        callBacks = MutableLiveData()
    }



    fun     deleteStudentTable(): MutableLiveData<String> {
        ExecuteAsyncTask(
            this@NewStudentViewModel,
            getApplication(),
            "deleteTable"
        ).execute(null)
        return callBacks
    }



    companion object {
        private class ExecuteAsyncTask(
            private val viewModel: NewStudentViewModel,
            private val application : Application,
            private val operation: String
        ) : AsyncTask<StudentModel, Void, String>() {
            override fun doInBackground(vararg studentModel: StudentModel): String {
                 if (operation == "deleteTable") {
                    viewModel.appDAO.deleteStudentTable()
                    viewModel.appDAO.deleteMarksTable()
                    viewModel.appDAO.deleteClassTable()
                     truncateTable(application,viewModel, "ClassModel")
                     truncateTable(application,viewModel, "MarksModel")
                     truncateTable(application,viewModel, "StudentModel")
                }
                return operation
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                if (result == "deleteTable") {
                    viewModel.callBacks.value = "Table Deleted"
                }
            }

        }

        private fun truncateTable(
           application : Application,
            viewModel: NewStudentViewModel,
            tableName: String
        ) {
            val database = SQLiteDatabase.openOrCreateDatabase(
                application.getDatabasePath(viewModel.appDB.openHelper.databaseName), null
            )

            if (database != null) {
                database.execSQL(
                    "UPDATE sqlite_sequence SET seq = 0 WHERE name = ?;",
                    arrayOf(tableName)
                )
            }
        }
    }


}
