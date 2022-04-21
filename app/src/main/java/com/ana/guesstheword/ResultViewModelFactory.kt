package com.ana.guesstheword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(private val finalResult:String): ViewModelProvider.Factory {// This Class implements this interface

    //Overriding this method, which the viewModel provider uses to create view Model objects
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // This checks if the viewModel the viewModel provider wants to create is the correct type. If so it returns one
        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(finalResult) as T
                    throw IllegalArgumentException("Unkow ViewModel") // if the view model type is incorrect throw an exception
    }
}