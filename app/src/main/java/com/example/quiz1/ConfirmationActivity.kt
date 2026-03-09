package com.example.quiz1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz1.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val type = intent.getStringExtra("EXTRA_TYPE")
        val date = intent.getStringExtra("EXTRA_DATE")
        val time = intent.getStringExtra("EXTRA_TIME")
        val gender = intent.getStringExtra("EXTRA_GENDER")

        binding.tvConfirmName.text = "Name: $name"
        binding.tvConfirmPhone.text = "Phone: $phone"
        binding.tvConfirmEmail.text = "Email: $email"
        binding.tvConfirmType.text = "Type: $type"
        binding.tvConfirmDate.text = "Date: $date"
        binding.tvConfirmTime.text = "Time: $time"
        binding.tvConfirmGender.text = "Gender: $gender"

        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}