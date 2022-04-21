package com.ana.guesstheword

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel (){// extends viewmodel

    val words = listOf("Android","Activity","Fragment","Java","Kotlin","Python","Machine Learning","JavaScript")
    val secretWord = words.random().uppercase()
    var secretWordDisplay = ""
    var correctGuesses = ""
    var incorrectGuesses = ""
    var livesLeft = 8

    init {// Calls the method deriveSecretWordDisplay(), This runs when the class is initialized
        secretWordDisplay = deriveSecretWordDisplay()
    }

    fun deriveSecretWordDisplay() : String {// How secret word should be displayed on the screen
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(str: String) = when(correctGuesses.contains(str)) {
       //Returns str if it appears in the secret word or "_" if it doesn't
        true -> str
        false -> "_"
    }

    fun makeGuess(guess: String){//This gets called when the user makes a guess
        if (guess.length == 1)
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            }else{
                incorrectGuesses += "$guess"
                livesLeft--
            }
    }

    fun isWon() =correctGuesses.equals(secretWordDisplay, true)
    // Checks whether the game has been won or lost
    fun isLost() =livesLeft <= 0

    fun wonLostMessage() :String{ // This generates a string when the game is over.
        var message = ""
        if (isWon()) message = "You Won!"
        else if (isLost()) message = "You Lost"
        message += "The word was $secretWord."
        return message
    }
}