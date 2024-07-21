package com.gereso.terramaster

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.gereso.terramaster.databinding.ActivityAddexpertBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddExpertActivity : ComponentActivity() {

    private lateinit var binding: ActivityAddexpertBinding
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddexpertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveButton = binding.saveButton
        saveButton.setOnClickListener {
            addExpert()
        }
    }

    private fun addExpert() {
        val fullname = binding.fullnameEditText.text.toString().trim()
        val specialty = binding.specialtyEditText.text.toString().trim()
        val introduction = binding.introductionEditText.text.toString().trim()
        val pricerange = binding.pricerangeEditText.text.toString().trim()
        val location = binding.locationEditText.text.toString().trim()
        val proposalsdone = binding.proposalsdoneEditText.text.toString().toInt()

        if (fullname.isEmpty() || specialty.isEmpty() || introduction.isEmpty() ||
            pricerange.isEmpty() || location.isEmpty() || proposalsdone == 0) {
            showToast("All fields are required.")
            return
        }

        val expert = ExpertModel(fullname, specialty, introduction, pricerange,proposalsdone, location)

        ApiClient.apiService.addExperts(expert).enqueue(object : Callback<ExpertModel> {
            override fun onResponse(call: Call<ExpertModel>, response: Response<ExpertModel>) {
                if (response.isSuccessful) {
                    val addedExpert = response.body()
                    if (addedExpert != null) {
                        showToast("Expert added successfully: ${addedExpert.fullname}")
                        finish()
                    } else {
                        showToast("Failed to add expert")
                    }
                } else {
                    showToast("Failed to add expert: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ExpertModel>, t: Throwable) {
                showToast("Request failed: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {

    }
}
