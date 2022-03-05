package com.example.firebasetestapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasetestapp.auth.AuthViewModel
import com.example.firebasetestapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        binding.tvDontHaveAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.btnLogin.setOnClickListener {

            login()
        }

        return binding.root
    }

    private fun login(){
        val email = binding.etLogin.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){

            viewModel.login(email, password)
            viewModel.currentUser.observe(this, {
                if(it!=null){
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMessengerFragment())
                }else{
                    Toast.makeText(requireContext(), "Try again", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

}