package com.ana.guesstheword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ana.guesstheword.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding
    lateinit var viewModel: GameViewModel // define the viewModel property, which will be initialized later in the code


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        val view = binding?.root

        //This line gets a GameViewModel object and assigns it to the viewModel Property
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)//The :: in this code is used to get a  reference to the GameViewModel class.  We have to use this syntax b/c the viewmodelprovider's get() method requires a reference to the GameViewModel class instead of GameViewModel object

        binding?.guessButton?.setOnClickListener(){
            //The makeguess(), isWon(), isLost(), and wonLostMessage() methods needs to be accessed via the viewModel property
            viewModel.makeGuess(binding!!.guess.text.toString().uppercase())
             binding!!.guess.text = null
            updateScreen()
            if (viewModel.isWon() || viewModel.isLost()){
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view?.findNavController()?.navigate(action)
            }
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Use the viewModel property to access the secretWordDisplay, livesLeft, and incorrectGuesses properties
    fun updateScreen() {
        binding?.word?.text = viewModel.secretWordDisplay
        binding?.lives?.text = "You have ${viewModel.livesLeft} lives left"
        binding?.incorrectGuesses?.text = "Incorrect guesses: ${viewModel.incorrectGuesses}"
    }
}