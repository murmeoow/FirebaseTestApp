package com.example.firebasetestapp.ui.registration

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasetestapp.auth.AuthViewModel
import com.example.firebasetestapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        return binding.root
    }


    private fun registerUser(){
        val email = binding.etReglogin.text.toString()
        val password = binding.etRegpassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    viewModel.register(email, password)
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())

                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}