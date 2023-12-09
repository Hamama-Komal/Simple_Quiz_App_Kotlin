package com.example.quizappinkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizappinkotlin.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To remove status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // To get value for previous activity
        val userName = intent.getStringExtra(setData.name)
        val score = intent.getStringExtra(setData.score)
        val image = intent.getIntExtra("imageNo",0)
       // val image : Int = this.intent.getIntExtra("${setData.avImg}",0)
        val totalQuestions = intent.getStringExtra("Total size")

        // Setting the values
        binding.congo.text = "Great Work ${userName}!"
        binding.Score.text = "${score} / ${totalQuestions}"

        // Toast.makeText(this,"$image",Toast.LENGTH_SHORT).show()
        binding.UserImg.setImageResource(when (image) {
            1 -> R.drawable.av1
            2 -> R.drawable.av2
            3 -> R.drawable.av3
            4 -> R.drawable.av4
            else -> R.drawable.dinosaur
        })


       when (score!!.toInt()) {
            in 0..3 -> {
                binding.remarks.text = "Beginner"
            }
            in 4..7 -> {
                binding.remarks.text = "Achiever"
            }
            else -> {
                binding.remarks.text = "Genius"
            }
        }


        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}



