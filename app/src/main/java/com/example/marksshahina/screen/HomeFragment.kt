package com.example.marksshahina.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.marksshahina.R
import com.example.marksshahina.database.MarksDatabase
import com.example.marksshahina.database.entity.User
import com.example.marksshahina.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val user = arguments?.getSerializable("user") as User

        var myDb = MarksDatabase.getInstance(requireContext())

        if (user.role == "Student") {
            var bundle = bundleOf("studentId" to myDb.studentSubjectDao().getStudentSubjectByUserId(user.userId)!!.id)
            findNavController().navigate(R.id.action_homeFragment_to_studentFragment2, bundle)
        }
        else {
            var bundle = bundleOf("user" to user)
            findNavController().navigate(R.id.action_homeFragment_to_teacherFragment, bundle)
        }



        return binding.root
    }
}