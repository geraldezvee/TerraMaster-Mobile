package com.gereso.terramaster

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.gereso.terramaster.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val emailAddressStr = binding.emailAddress.text.toString().trim()
            val passwordStr = binding.password.text.toString().trim()

            if (emailAddressStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(this@MainActivity, "Email Address and Password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(emailAddressStr,passwordStr)
            loginAccount(loginRequest)

            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        tvSignup = binding.tvSignup
        tvSignup.setOnClickListener {
            navigateToSignup()
        }
    }

    private fun loginAccount(credentials: LoginRequest) {
        ApiClient.apiService.loginAccount(credentials).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    showToast("Login")

                } else {
                    showToast("Incorrect Credentials")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                    showToast("Login Successful")
                t.message?.let { Log.e("help", it) }
            }
        })
    }

    private fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun navigateToSignup() {
        val intent = Intent(this@MainActivity, SignupActivity::class.java)
        startActivity(intent)
    }
}
