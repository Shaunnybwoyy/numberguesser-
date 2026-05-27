package com.example.numberguesser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. The random number the user needs to guess
        val targetNumber = (1..100).random()
        var guessCount = 0

        // 2. Find the UI elements
        val guessEditText = findViewById<EditText>(R.id.guessEditText)
        val guessButton = findViewById<Button>(R.id.guessButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // 3. Set up the button click listener
        guessButton.setOnClickListener {
            val userGuessString = guessEditText.text.toString()
            
            if (userGuessString.isNotEmpty()) {
                val userGuess = userGuessString.toInt()
                guessCount++
                
                // 4. Compare the guess and update the result text
                when {
                    userGuess < targetNumber -> {
                        resultTextView.text = "Your number is too low! (Guess #$guessCount)"
                    }
                    userGuess > targetNumber -> {
                        resultTextView.text = "Your number is too high! (Guess #$guessCount)"
                    }
                    else -> {
                        resultTextView.text = "Your number is correct! You win in $guessCount guesses!"
                    }
                }
            } else {
                resultTextView.text = "Please enter a number first."
            }
        }
    }
}