package com.dc.storedataoffline.offlinecrud.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dc.storedataoffline.offlinecrud.model.*

@Dao
interface AppDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(studentModel: StudentModel)

    @Query("DELETE FROM StudentModel")
    fun deleteStudentTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMark(marksModel: MarksModel)

    @Query("DELETE FROM MarksModel")
    fun deleteMarksTable()

    @Query("SELECT * FROM StudentModel")
    fun getStudentList() : LiveData<List<MyClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClass(classModel: ClassModel)

    @Query("SELECT * FROM CLASSMODEL where classId=:classId")
    fun getClassDetails(classId: String) : LiveData<ClassModel>

    @Query("DELETE FROM ClassModel")
    fun deleteClassTable()

    @Query("SELECT * FROM MarksModel where studentId=:studentId")
    fun getMarksListById(studentId: String) : LiveData<List<MarksModel>>
}