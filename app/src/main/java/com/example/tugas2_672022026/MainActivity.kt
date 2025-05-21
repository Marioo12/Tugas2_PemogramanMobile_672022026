package com.example.tugas2_672022026

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugas2_672022026.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener {
            val fullname = binding.editTextFullname.text.toString()
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()

            when {
                fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    Toast.makeText(this, "Tolong isi semua form", Toast.LENGTH_SHORT).show()
                }
                fullname.any { it.isDigit() } -> {
                    Toast.makeText(this, "Nama lengkap tidak boleh mengandung angka", Toast.LENGTH_SHORT).show()
                }
                username.any { it.isDigit() } -> {
                    Toast.makeText(this, "Username tidak boleh mengandung angka", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.putString("password", password)
                    editor.putString("fullname", fullname)
                    editor.apply()

                    Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()

                    binding.editTextFullname.text.clear()
                    binding.editTextUsername.text.clear()
                    binding.editTextPassword.text.clear()
                    binding.editTextConfirmPassword.text.clear()

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
