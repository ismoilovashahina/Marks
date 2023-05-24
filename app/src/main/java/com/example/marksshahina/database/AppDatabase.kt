package com.example.marksshahina.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.marksshahina.database.entity.User
import com.example.marksshahina.database.dao.*
import com.example.marksshahina.database.entity.*


@Database(
    entities = [
        User::class,
        TeacherSubject::class,
        StudentSubject::class,
        Subject::class,
        Group::class,
        Mark::class
    ],
    version = 1,
)
abstract class MarksDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun teacherSubjectDao(): TeacherSubjectDao
    abstract fun studentSubjectDao(): StudentSubjectDao
    abstract fun subjectDao(): SubjectDao
    abstract fun groupDao(): GroupDao
    abstract fun markDao(): MarkDao

    companion object {
        private const val DATABASE_NAME = "marks_database"

        @Volatile
        private var INSTANCE: MarksDatabase? = null

        fun getInstance(context: Context): MarksDatabase  {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MarksDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE!!
        }
    }
}
