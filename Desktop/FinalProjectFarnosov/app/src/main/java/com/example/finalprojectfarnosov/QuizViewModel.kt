package com.example.finalprojectfarnosov

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
      val questionBankTest = listOf(
        Question(R.string.QuestionTest1, true),
        Question(R.string.QuestionTest2, true),
        Question(R.string.QuestionTest3, true),
        Question(R.string.QuestionTest4, true),
        Question(R.string.QuestionTest5, false),
        Question(R.string.QuestionTest6, false))
     var currentIndex = 0
     var ShowMesageBlock = 0
     var countAnswer = 0
     var Procent = 0.1
    val currentQuestionAnswer: Boolean
         get() =questionBankTest[currentIndex].answer
    val currentQuestionText: Int

        get() =
            questionBankTest[currentIndex].textResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) %
                questionBankTest.size
    }
    fun moveToBack() {
        if(currentIndex==0)currentIndex=+questionBankTest.size
        currentIndex = (currentIndex  -1) %questionBankTest.size

    }



}

