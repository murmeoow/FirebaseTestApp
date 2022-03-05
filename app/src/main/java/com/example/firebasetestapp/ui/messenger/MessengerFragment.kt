package com.example.firebasetestapp.ui.messenger

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasetestapp.auth.AuthViewModel
import com.example.firebasetestapp.R
import com.example.firebasetestapp.databinding.FragmentMessengerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessengerFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentMessengerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessengerBinding.inflate(inflater, container, false )

        setHasOptionsMenu(true)

        viewModel.currentUser.observe(this,{
            binding.textview.text = it?.email
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.logout()
        findNavController().navigate(MessengerFragmentDirections.actionMessengerFragmentToLoginFragment())
        return true
    }

}