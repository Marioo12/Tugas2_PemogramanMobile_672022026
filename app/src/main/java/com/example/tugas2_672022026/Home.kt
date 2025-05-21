package com.example.tugas2_672022026

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas2_672022026.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Gunakan binding untuk layout Home, bukan Login
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val fullname = sharedPreferences.getString("fullname", "User")

        // Tampilkan nama pengguna di TextView
        binding.textWelcome.text = "Welcome\n$fullname"
    }
}