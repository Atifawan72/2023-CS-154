package com.example.quiz1

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz1.databinding.ActivityBookAppointmentBinding
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookAppointmentBinding
    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupDateAndTimePickers()

        binding.btnConfirm.setOnClickListener {
            validateAndConfirm()
        }
    }

    private fun setupSpinner() {
        val options = arrayOf(
            "Select Appointment Type",
            "Doctor Consultation",
            "Dentist Appointment",
            "Eye Specialist",
            "Skin Specialist",
            "General Checkup"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter
    }

    private fun setupDateAndTimePickers() {
        val calendar = Calendar.getInstance()

        binding.btnPickDate.setOnClickListener {
            DatePickerDialog(this, { _, year, month, day ->
                selectedDate = "$day/${month + 1}/$year"
                binding.tvSelectedDateTime.text = "Date: $selectedDate | Time: $selectedTime"
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btnPickTime.setOnClickListener {
            TimePickerDialog(this, { _, hour, minute ->
                selectedTime = String.format("%02d:%02d", hour, minute)
                binding.tvSelectedDateTime.text = "Date: $selectedDate | Time: $selectedTime"
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }

    private fun validateAndConfirm() {
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val type = binding.spinnerType.selectedItem.toString()
        val genderId = binding.rgGender.checkedRadioButtonId
        val acceptedTerms = binding.cbTerms.isChecked

        if (name.isEmpty()) {
            Toast.makeText(this, "Full Name is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, "Phone Number is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Email Address is required", Toast.LENGTH_SHORT).show()
            return
        }
        if (type == "Select Appointment Type") {
            Toast.makeText(this, "Please select an Appointment Type", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
            Toast.makeText(this, "Please select Date and Time", Toast.LENGTH_SHORT).show()
            return
        }
        if (genderId == -1) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show()
            return
        }
        if (!acceptedTerms) {
            Toast.makeText(this, "You must agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
            return
        }

        val gender = when (genderId) {
            R.id.rbMale -> "Male"
            R.id.rbFemale -> "Female"
            else -> "Other"
        }

        val intent = Intent(this, ConfirmationActivity::class.java).apply {
            putExtra("EXTRA_NAME", name)
            putExtra("EXTRA_PHONE", phone)
            putExtra("EXTRA_EMAIL", email)
            putExtra("EXTRA_TYPE", type)
            putExtra("EXTRA_DATE", selectedDate)
            putExtra("EXTRA_TIME", selectedTime)
            putExtra("EXTRA_GENDER", gender)
        }
        startActivity(intent)
    }
}