package com.gereso.terramaster

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.gereso.terramaster.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : ComponentActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val expertsList = mutableListOf<String>()
    private lateinit var addexpert: Button
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.expertlist
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, expertsList)
        listView.adapter = adapter

        refreshButton = binding.refreshButton
        refreshButton.setOnClickListener {
            fetchExperts()
        }

        fetchExperts()

        addexpert = binding.addexpert
        addexpert.setOnClickListener {
            navigateToAddExpert()
        }
    }

    private fun fetchExperts() {
        val apiService = ApiClient.apiService.getAllExperts()
        apiService.enqueue(object : Callback<List<FetchAll>> {
            override fun onResponse(call: Call<List<FetchAll>>, response: Response<List<FetchAll>>) {
                if (response.isSuccessful) {
                    val experts = response.body()
                    if (experts != null) {
                        updateListView(experts)
                    } else {
                        showToast("No experts found")
                    }
                } else {
                    showToast("Failed to fetch experts: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<FetchAll>>, t: Throwable) {
                showToast("Request failed: ${t.message}")
            }
        })
    }

    private fun updateListView(experts: List<FetchAll>) {
        expertsList.clear()
        for (expert in experts) {
            expertsList.add(expert.fullname)
        }
        adapter.notifyDataSetChanged()
    }

    private fun showToast(message: String) {

    }
    private fun navigateToAddExpert() {
        val intent = Intent(this, AddExpertActivity::class.java)
        startActivity(intent)
    }
}