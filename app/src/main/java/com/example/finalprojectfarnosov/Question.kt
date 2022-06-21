package com.example.finalprojectfarnosov

import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int, val answer: Boolean)
data class QuestionTest(@StringRes val textResId:Int, val answer: Boolean)