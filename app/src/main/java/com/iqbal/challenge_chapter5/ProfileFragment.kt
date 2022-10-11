package com.iqbal.challenge_chapter5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.iqbal.challenge_chapter5.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    lateinit var binding : FragmentProfileBinding
    lateinit var ViewModel : ViewModel
    lateinit var sharedpref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedpref = requireContext().getSharedPreferences("userdata",Context.MODE_PRIVATE)

        val username = sharedpref.getString("username","")
        val password = sharedpref.getString("password","")

        binding.NameProfile.setText(username)
        binding.EmailProfile.setText(password)

        binding.btnUpdate.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_updateDataFragment)
        }

        binding.btnLogout.setOnClickListener{
            var pref = sharedpref.edit()
            pref.clear()
            pref.apply()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            Toast.makeText(context,"berhasil logout",Toast.LENGTH_SHORT).show()
        }


    }

//    fun updateDataUser() {
//        var name = binding.NameProfile.text.toString()
//        var password = binding.EmailProfile.text.toString()
//        model
//    }

}