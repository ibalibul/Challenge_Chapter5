package com.iqbal.challenge_chapter5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.SplashScreen
import androidx.navigation.Navigation
import com.iqbal.challenge_chapter5.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    lateinit var  sharedpref : SharedPreferences
    lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)

        binding = FragmentSplashBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedpref = requireContext().getSharedPreferences("userdata", Context.MODE_PRIVATE)
        val status = sharedpref.getString("session","false")

        Handler().postDelayed({
            if(status=="false")
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
            else if(status=="true")
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
        }, 3000)
    }

}