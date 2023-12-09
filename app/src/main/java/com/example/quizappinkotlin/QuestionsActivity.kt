package com.example.quizappinkotlin

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.quizappinkotlin.databinding.ActivityQuestionsBinding
import com.google.android.material.snackbar.Snackbar

class QuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionsBinding

    private var userName: String? = null
    private var score: Int = 0
    private var value: Int? = null

    private var questionList: ArrayList<QuestionData>? = null
    private var currentPosition: Int = 1
    private var selectedOption: Int? = 0
    private var selectedOptionBoolean : Boolean = false
    private lateinit var timer: CountDownTimer

    // private var clickCounter : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set User Name
        userName = intent.getStringExtra(setData.name)
        binding.Uname.text = "${userName}"
        // Set Selected Avatar
        value = intent.getIntExtra("${setData.avImg}", 0)
        setAvatar()

        showDialogBox()

        // showSnackBar()
        // setTimer()
        // Set questions

        questionList = setData.getQuestion()
        if(selectedOption == 0) {
            setQuestion()
        }else{

        }

        setSelectedOptionStyle()
        binding.submit.setOnClickListener {
            onClickSubmit()
        }


    }

    private fun setAvatar() {

        intent.putExtra("${setData.avImg}", value)
        binding.avImg.setImageResource(
            when (value) {
                1 -> R.drawable.av1
                2 -> R.drawable.av2
                3 -> R.drawable.av3
                4 -> R.drawable.av4
                else -> R.drawable.dinosaur

            }
        )

    }

    private fun showSnackBar() {

        val snackbar = Snackbar.make(binding.root, "Are You Ready?", Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
            .setActionTextColor(Color.parseColor("#4169E1"))
        snackbar.setAction("Start", View.OnClickListener {
            setTimer()
        })
        snackbar.show()

    }

    private fun showDialogBox() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are You Ready?")
        builder.setMessage("You have 50 second to solve Quiz.")
        builder.setCancelable(false)
        builder.setPositiveButton("Let's Start", DialogInterface.OnClickListener { _, _ ->
            // Handle click event
            setTimer()
        })
        builder.setNegativeButton("Back", DialogInterface.OnClickListener { _, _ ->
            // Handle click event
            startActivity(Intent(this@QuestionsActivity, MainActivity::class.java))
            timer.cancel()
            finish()
        })
        val alertDialog = builder.create()
        alertDialog.show()

    }


    private fun setTimer() {

        // time count down for 50 seconds,
        // with 1 second as countDown interval
        timer = object : CountDownTimer(50000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.setText("${millisUntilFinished / 1000}")
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                binding.timer.setText("0")
                Toast.makeText(this@QuestionsActivity, "Time Out!", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@QuestionsActivity, ResultActivity::class.java)
                timer.cancel()
                intent.putExtra(setData.name, userName)
                intent.putExtra("imageNo", value)
                intent.putExtra(setData.score, score.toString())
                intent.putExtra("Total size", questionList!!.size.toString())
                startActivity(intent)
                finish()
            }
        }.start()

    }

    private fun setSelectedOptionStyle() {

        // For changing style of one selected option
        binding.opt1.setOnClickListener {
            selectedOptionBoolean = true
            selectedOptionStyle(binding.opt1, 1)
        }

        binding.opt2.setOnClickListener {
            selectedOptionBoolean = true
            selectedOptionStyle(binding.opt2, 2)
        }

        binding.opt3.setOnClickListener {
            selectedOptionBoolean = true
            selectedOptionStyle(binding.opt3, 3)
        }

        binding.opt4.setOnClickListener {
            selectedOptionBoolean = true
            selectedOptionStyle(binding.opt4, 4)
        }
    }



    private fun onClickSubmit() {

        // When an option is selected

        binding.submit.text = "Submit"
         if (selectedOption != 0) {

            val question = questionList!![currentPosition - 1]
            // highlight Right or Wrong option
            if (selectedOption != question.correct_ans) {
                setColor(selectedOption!!, R.drawable.wrong_selected_option)
            } else {
                score++
            }

            setColor(question.correct_ans, R.drawable.correct_selected_option)

            // change the text on button
            if (currentPosition == questionList!!.size) {
                binding.submit.text = "Finish"
                currentPosition++

            } else {

                binding.submit.text = "Go to next"


            }
        }

        // When no option is selected
        else {


                when {
                    (currentPosition <= questionList!!.size) -> {
                          if(selectedOptionBoolean){
                              currentPosition++
                              setQuestion()
                              selectedOptionBoolean = false
                          }
                          else{
                              Toast.makeText(this,"Select an option",Toast.LENGTH_SHORT).show()
                          }

                      }

                     else -> {

                        var intent = Intent(this, ResultActivity::class.java)
                        timer.cancel()
                        intent.putExtra(setData.name, userName)
                        intent.putExtra("imageNo", value)
                        intent.putExtra(setData.score, score.toString())
                        intent.putExtra("Total size", questionList!!.size.toString())
                        startActivity(intent)
                        finish()

                    }
                }
            }

        // set value of selected option to default (zero)
        selectedOption = 0
        //   clickCounter++
    }


    // set new question and options
    private fun setQuestion() {

        var question = questionList!![currentPosition - 1]
        setOptionStyle()

        // Update progress bar and its value
        binding.progressBar.progressDrawable.setColorFilter(
            Color.parseColor("#FF0F7FB1"), PorterDuff.Mode.SRC_IN
        )
        binding.progressBar.progress = currentPosition
        binding.progressBar.max = questionList!!.size
        binding.progressText.text = "${currentPosition}" + "/" + "${questionList!!.size}"


        // update questions and options
        binding.questionText.text = question.question
        binding.opt1.text = question.option_one
        binding.opt2.text = question.option_two
        binding.opt3.text = question.option_three
        binding.opt4.text = question.option_four


    }


    // Set a Default Style for all options
    fun setOptionStyle() {

        // make an arraylist of all options(TextView)
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, binding.opt1)
        optionList.add(1, binding.opt2)
        optionList.add(2, binding.opt3)
        optionList.add(3, binding.opt4)

        for (i in optionList) {
            i.setTextColor(Color.parseColor("#424141"))
            i.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            i.typeface = Typeface.DEFAULT
        }

    }

    // Highlight the One selected option
    fun selectedOptionStyle(textView: TextView, opt: Int) {

        setOptionStyle()
        selectedOption = opt

        textView.setTextColor(Color.parseColor("#000000"))
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        textView.typeface = Typeface.DEFAULT_BOLD

    }


    // to change the color of options background
    fun setColor(opt: Int, color: Int) {
        when (opt) {

            1 -> { binding.opt1.background = ContextCompat.getDrawable(this, color) }

            2 -> { binding.opt2.background = ContextCompat.getDrawable(this, color) }

            3 -> { binding.opt3.background = ContextCompat.getDrawable(this, color) }

            4 -> { binding.opt4.background = ContextCompat.getDrawable(this, color) }


        }
    }

    override fun onBackPressed() {
        timer.cancel()
        super.onBackPressed()
    }

}