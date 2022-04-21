package com.ana.guesstheword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ana.guesstheword.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container,false)
        val view = binding.root
        // Inflate the layout for this fragment

        // set the textView to the result string that's passed from GameFragment
        val result = ResultFragmentArgs.fromBundle(requireArguments()).result// get the string that is getting passed to result Fragment
        viewModelFactory = ResultViewModelFactory(result)//Create a viewModel factory object

        //Pass the view Modal factory to the view model provider
        viewModel = ViewModelProvider(this,viewModelFactory).get(ResultViewModel::class.java) // t

        binding.wonLost.text = viewModel.result
        binding.newGameButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_gameFragment)
            //navigate to GameFragment When the user clicks the button
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}