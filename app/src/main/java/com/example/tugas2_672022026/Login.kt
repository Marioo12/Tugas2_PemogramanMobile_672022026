package com.example.tugas2_672022026

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas2_672022026.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tolong isi semua form", Toast.LENGTH_SHORT).show()
            } else if (username.any { it.isDigit() }) {
                Toast.makeText(this, "Username tidak boleh mengandung angka", Toast.LENGTH_SHORT).show()
            } else {
                val savedUsername = sharedPreferences.getString("username", "")
                val savedPassword = sharedPreferences.getString("password", "")
                val savedFullname = sharedPreferences.getString("fullname", "User")

                if (username == savedUsername && password == savedPassword) {
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, Home::class.java)
                    intent.putExtra("fullname", savedFullname)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}