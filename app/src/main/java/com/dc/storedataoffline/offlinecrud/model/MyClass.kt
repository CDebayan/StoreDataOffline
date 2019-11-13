package com.dc.storedataoffline.offlinecrud.model

import androidx.room.Embedded
import androidx.room.Relation

data class MyClass(
    @Embedded val studentModel: StudentModel,
    @Relation(
        parentColumn = "studentsId",
        entityColumn = "studentId",
        entity = MarksModel::class
    )
    val marks : List<MarksModel>
)