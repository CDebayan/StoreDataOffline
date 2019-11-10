package com.dc.storedataoffline.offlinecrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dc.storedataoffline.offlinecrud.model.ClassModel
import com.dc.storedataoffline.offlinecrud.model.MarksModel
import com.dc.storedataoffline.offlinecrud.model.StudentModel


@Database(
    entities = [StudentModel::class,MarksModel::class,ClassModel::class],
    exportSchema = false,
    version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao(): AppDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "student.db")
            .build()
    }
}