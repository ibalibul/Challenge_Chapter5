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
import androidx.navigation.fragment.findNavController
import com.iqbal.challenge_chapter5.databinding.FragmentLoginBinding
import com.iqbal.challenge_chapter5.model.GetResponseUserNewItem
import com.iqbal.challenge_chapter5.model.GetUserResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import com.iqbal.challenge_chapter5.viewmodel.ViewModelUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


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
                loginfilm(inputUsername!!,inputPassword!!)
            }

        binding.txtRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }



        binding.txtIndonesia.setOnClickListener{
            setLocale("id")
        }
        binding.txtEnglish.setOnClickListener{
            setLocale("en")
        }

    }
    fun loginfilm(name: String, password: String) {
        APIClient.instance.getAllUser()
            .enqueue(object : Callback<List<GetResponseUserNewItem>> {
                override fun onResponse(
                    call: Call<List<GetResponseUserNewItem>>,
                    response: Response<List<GetResponseUserNewItem>>
                ) {
                    var data = false
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val respon = response.body()
                            for (i in 0 until respon!!.size) {
                                if (respon[i].username == name && respon[i].password == password
                                ) {
                                    data = true

                                    var addUser = sharedpref.edit()
                                    addUser.putString("id", respon[i].id)
                                    addUser.putString("username", name)
                                    addUser.putString("password", password)
                                    addUser.putString("name", respon[i].name)
                                    addUser.putString("session","true")
                                    addUser.apply()

                                    Toast.makeText(context, "Login Sukses", Toast.LENGTH_SHORT)
                                        .show()
                                    Navigation.findNavController(view!!)
                                        .navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }
                            if (data == false) Toast.makeText(
                                context,
                                "Wrong  Username or Password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetResponseUserNewItem>>, t: Throwable) {

                }

            })
    }

    fun setLocale (lang : String){
        val lokal = Locale(lang)
        val conf = resources.configuration
        conf.locale = lokal
        resources.updateConfiguration(conf, resources.displayMetrics)
        activity?.startActivity(Intent(activity,MainActivity::class.java))
    }
}