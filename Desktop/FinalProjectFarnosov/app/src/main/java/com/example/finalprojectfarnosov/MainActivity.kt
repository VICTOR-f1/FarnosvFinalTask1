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
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
      lateinit var  trueButton:Button
      lateinit var  falseButton:Button
     lateinit var nextButton: Button
     lateinit var backButton: Button

     lateinit var questionTextView:TextView
     val questionBankTest = listOf(
        Question(R.string.QuestionTest1, true),
        Question(R.string.QuestionTest2, true),
        Question(R.string.QuestionTest3, true),
        Question(R.string.QuestionTest4, true),
        Question(R.string.QuestionTest5, false),
        Question(R.string.QuestionTest6, false))

     var CountCorect = 0
     var ShowMesageBlock = 0
     var countAnswer = 0
     var Procent = 0.1
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        val  currentIndex =savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex



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
            quizViewModel.moveToNext()
            updateQuestion()
        }
        backButton.setOnClickListener {
            quizViewModel.moveToBack()
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
    override fun
            onSaveInstanceState(savedInstanceState: Bundle)
    {
        super.onSaveInstanceState(savedInstanceState)

        Log.i(TAG,
            "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX,
            quizViewModel.currentIndex)
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
        val questionTextResId = quizViewModel.currentQuestionText

        questionTextView.setText(questionTextResId)
    }
     fun checkAnswer(userAnswer:Boolean){
         val correctAnswer =   quizViewModel.currentQuestionAnswer


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
        if(ShowMesageBlock==1){ Toast.makeText(this, " Block ",   Toast.LENGTH_SHORT).show()}
         quizViewModel.moveToNext()
        updateQuestion()
        if(countAnswer==6){
            Procent=  (CountCorect/6.0)*100;
            trueButton.isEnabled=false
            falseButton.isEnabled = false
            Toast.makeText(this, "количество правильных ответов  "+CountCorect+" процент "+Procent,   Toast.LENGTH_SHORT).show()}
        }


    }

