package com.example.marksshahina.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.marksshahina.R
import com.example.marksshahina.database.MarksDatabase
import com.example.marksshahina.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var myDb: MarksDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        myDb = MarksDatabase.getInstance(requireContext())


        binding.signUpInSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                val user = myDb.userDao().login(login, password)

                if (user != null) {
                    var bundle = bundleOf("user" to user)
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment, bundle)
                } else {
                    Toast.makeText(requireContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter your login credentials", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
