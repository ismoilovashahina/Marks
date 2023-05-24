package com.example.marksshahina.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marksshahina.R
import com.example.marksshahina.adapters.StudentMarksAdapter
import com.example.marksshahina.database.MarksDatabase
import com.example.marksshahina.databinding.FragmentStudentBinding


class StudentFragment : Fragment() {
    private lateinit var myDb: MarksDatabase
    private lateinit var studentMarksAdapter: StudentMarksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStudentBinding.inflate(inflater, container, false)

        myDb = MarksDatabase.getInstance(requireContext())

        var studentId = arguments?.getInt("studentId")

        studentMarksAdapter = StudentMarksAdapter(
            subjects = myDb.subjectDao().getAllSubjects(),
            context = requireContext(),
            myDb = myDb,
            studentId = studentId!!
        )
        binding.rvStudentSubjectMarks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStudentSubjectMarks.adapter = studentMarksAdapter

        binding.logout.setOnClickListener {
            findNavController().navigate(R.id.action_studentFragment_to_signInFragment)
        }

        return binding.root
    }
}