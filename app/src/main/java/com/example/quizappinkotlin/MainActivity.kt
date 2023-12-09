package com.example.quizappinkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizappinkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To remove status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN



        binding.button.setOnClickListener {


            // To get Avatar Image No
            val avNo: Int = when {
                binding.rbAv1.isChecked -> 1
                binding.rbAv2.isChecked -> 2
                binding.rbAv3.isChecked -> 3
                binding.rbAv4.isChecked -> 4
                else -> 0
            }

            // Toast.makeText(this@MainActivity,avNo.toString(),Toast.LENGTH_SHORT).show()


            // Check points and jump to next activity
            if (binding.input.text.toString().trim().isEmpty()) {

                binding.input.error = "Name Can not be empty"
                //Toast.makeText(this@MainActivity, "Enter your name", Toast.LENGTH_SHORT).show()
            } else if (avNo == 0) {

                Toast.makeText(this@MainActivity, "Select an avatar", Toast.LENGTH_SHORT).show()
            } else {

                var intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra("${setData.name}", binding.input.text.toString())
                intent.putExtra("${setData.avImg}", avNo)
                startActivity(intent)
                finish()
            }
        }
    }

}