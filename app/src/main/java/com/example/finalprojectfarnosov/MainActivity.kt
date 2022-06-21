package com.example.finalprojectfarnosov

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private  lateinit var  trueButton:Button
    private  lateinit var  falseButton:Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button

    private lateinit var questionTextView:TextView
    private val questionBankTest = listOf(
        Question(R.string.QuestionTest1, true),
        Question(R.string.QuestionTest2, true),
        Question(R.string.QuestionTest3, true),
        Question(R.string.QuestionTest4, true),
        Question(R.string.QuestionTest5, false),
        Question(R.string.QuestionTest6, false))
    private var currentIndex = 0
    private var CountCorect = 0
    private var ShowMesageBlock = 0
    private var countAnswer = 0
    private var Procent = 0.1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        trueButton=findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)

        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener { view: View ->
            ShowMesageBlock++
            nextButton.isEnabled=false
            backButton.isEnabled = false
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            ShowMesageBlock++
            nextButton.isEnabled=false
            backButton.isEnabled = false
            checkAnswer(false)
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) %questionBankTest.size
            updateQuestion()
        }
        backButton.setOnClickListener {
            if(currentIndex==0)currentIndex=+questionBankTest.size
            currentIndex = (currentIndex  -1) %questionBankTest.size
            updateQuestion()
        }


        updateQuestion()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    fun updateQuestion() {
        val questionTextResId = questionBankTest[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBankTest[currentIndex].answer
       /* val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }
        else {R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId,   Toast.LENGTH_SHORT).show()

        */
        countAnswer++
        if (userAnswer == correctAnswer) {
            CountCorect++
        }
        if(ShowMesageBlock==1){ Toast.makeText(this, "Block",   Toast.LENGTH_SHORT).show()}
        currentIndex = (currentIndex + 1) %questionBankTest.size
        updateQuestion()
        if(countAnswer==6){
            Procent=  (CountCorect/6.0)*100;
            trueButton.isEnabled=false
            falseButton.isEnabled = false
            Toast.makeText(this, "количество правильных ответов"+CountCorect+"процент"+Procent,   Toast.LENGTH_SHORT).show()}
        }


    }

