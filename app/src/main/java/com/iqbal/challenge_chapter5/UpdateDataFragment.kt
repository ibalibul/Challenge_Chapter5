package com.iqbal.challenge_chapter5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.iqbal.challenge_chapter5.databinding.FragmentUpdateDataBinding
import com.iqbal.challenge_chapter5.viewmodel.ViewModelUser


class UpdateDataFragment : Fragment() {


    lateinit var shrepref : SharedPreferences
    lateinit var binding : FragmentUpdateDataBinding
    lateinit var viewModel : ViewModelUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        shrepref = requireActivity().getSharedPreferences("userdata",Context.MODE_PRIVATE)

        var username = shrepref.getString("username","")
        var fpassword = shrepref.getString("password","")


        binding.edUpdateUser.setText(username)
        binding.edUpdatePassword.setText(fpassword)

        binding.btnSimpan.setOnClickListener{
            setDataToInput()

        }





    }



    fun setDataToInput(){

        var add = shrepref.getString("id","")
        var fname = shrepref.getString("name","")


        var username = binding.edUpdateUser.text.toString()
        var password = binding.edUpdatePassword.text.toString()

        viewModel.UpdateDataUser(add!!,fname!!,username,password)
        viewModel.updateLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(requireContext(),"update Sukses",Toast.LENGTH_SHORT).show()
            }

        })
    }


}