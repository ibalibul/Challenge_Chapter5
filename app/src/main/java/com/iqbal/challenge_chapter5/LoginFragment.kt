package com.iqbal.challenge_chapter5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.iqbal.challenge_chapter5.databinding.FragmentLoginBinding
import com.iqbal.challenge_chapter5.model.GetUserResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import com.iqbal.challenge_chapter5.viewmodel.ViewModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var userVm : ViewModelUser
    lateinit var sharedpref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedpref = requireActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            var inputUsername = binding.edUser.text.toString()
            var inputPassword = binding.edPassword.text.toString()

            var userName = sharedpref.getString("username", "")
            var password = sharedpref.getString("password", "")
        }


            binding.txtRegister.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_loginFragment_to_registerFragment)
            }

            binding.btnLogin.setOnClickListener {
                var inputUsername = binding.edUser.text.toString()
                var inputpassword = binding.edPassword.text.toString()

                if (inputUsername.equals("") || password.equals("")){
                 Toast.makeText(context,"Fill In Username and Password!",Toast.LENGTH_SHORT)
                }
                else {
                    if (inputUsername.equals(userName)){
                        if (inputpassword.equals(password)){
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                        else Toast.makeText(context,"Password Invalid",Toast.LENGTH_SHORT).show()
                    }
                        else Toast.makeText(context,"Username Invalid!" , Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    fun loginfilm(name : String, password : String) {
        APIClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetUserResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetUserResponseItem>>,
                    response: Response<List<GetUserResponseItem>>
                ) {
                    var data = false
                   if (response.isSuccessful){
                    if (response.body() != null){
                        val respon = response.body()
                        for (i in 0 until respon!!.size){
                            if(respon[i].name.equals(name)&& respon[i].password.equals(password)){
                                data = true

                            var addUser = sharedpref.edit()
                                addUser.putString("id",respon[i].id)
                                addUser.putString("username",name)
                                addUser.putString("password",password)
                                addUser.putString("name",respon[i].name)
                                addUser.putString("age",respon[i].password)
                                addUser.apply()

                                Toast.makeText( context,"Login Sukses", Toast.LENGTH_SHORT).show()
                                Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                        }
                        if (data == false) Toast.makeText(context ,"Wrong  Username or Password", Toast.LENGTH_SHORT).show()
                    }
                   }
                }

                override fun onFailure(call: Call<List<GetUserResponseItem>>, t: Throwable) {

                }

            })
    }
}