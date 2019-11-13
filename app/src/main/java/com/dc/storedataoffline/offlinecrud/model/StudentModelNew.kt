package com.dc.storedataoffline.offlinecrud.model


data class StudentModelNew(

    var studentsId: String? = "",


    var studentsName: String? = "",


    var studentsRoll: String? = "",


    var classId: String? = "",

    var marksList: List<MarksModel>? = null

) {





}