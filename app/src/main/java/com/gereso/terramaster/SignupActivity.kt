package com.gereso.terramaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.gereso.terramaster.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : ComponentActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var nickNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        nickNameEditText = findViewById(R.id.nickNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signupButton = findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val firstName = firstNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val nickname = nickNameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showToast("All fields are required")
            return
        }

        val newUser = UserModel(firstName, lastName, nickname, email, password)

        ApiClient.apiService.signupAccount(newUser).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val message = response.body()
                    if (message != null && message.contains("successfully")) {
                        showToast("Registration successful")
                        navigateToLogin()
                    } else {
                        showToast("Failed to register: $message")
                    }
                } else {
                    showToast("Failed to register: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                showToast("Request failed: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {

    }

    private fun navigateToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
