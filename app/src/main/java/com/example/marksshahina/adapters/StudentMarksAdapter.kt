package com.example.marksshahina.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marksshahina.database.MarksDatabase
import com.example.marksshahina.database.entity.Subject
import com.example.marksshahina.databinding.StudentMarkItemBinding


class StudentMarksAdapter(
    private val subjects: List<Subject>,
    val context: Context,
    private val studentId: Int,
    private var myDb: MarksDatabase
) : RecyclerView.Adapter<StudentMarksAdapter.StudentMarksHolder>() {

    class StudentMarksHolder(itemView: StudentMarkItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val subjectName = itemView.studentSubject
        val studentMark = itemView.studentMark
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentMarksHolder {
        return StudentMarksHolder(StudentMarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: StudentMarksHolder, position: Int) {
        val subject = subjects[position]

        holder.subjectName.text = myDb.subjectDao().getSubjectById(subjectId = subject.subjectId)!!.name
        var mark = myDb.markDao().getMarkByStudentSubjectIdAndSubjectId(studentSubjectId = studentId, subjectId = subject.subjectId)?.mark
        Log.d("FAN ${holder.subjectName.text}", "BAHO - $mark")

        if (mark != null) {
            holder.studentMark.text = mark.toString()
        }
        else {
            holder.studentMark.text = ""
        }
    }
}