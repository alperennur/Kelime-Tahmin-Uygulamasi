package com.alperennur.kelimetahmin.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.alperennur.kelimetahmin.R
import com.alperennur.kelimetahmin.WordList
import com.alperennur.kelimetahmin.databinding.FragmentGuessPageBinding


class GuessPageFragment : Fragment() {

    private var _binding: FragmentGuessPageBinding?=null
    private val binding get() = _binding!!
    var random : Int ? = null
    private var wordcontain = WordList()
    var word : String?=null
    var temp : String?=null
    var guess: String?=null
    var greenContainList = HashSet<Char>()
    var yellowContainList= HashSet<Char>()
    var guessNumber = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentGuessPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        random= (0..14).random()


        binding.guessButton.setOnClickListener {

            binding.hintText.text="Hint:"
            guess = binding.guessEditText.text.toString().toLowerCase()
            word = wordcontain.wordList.get(random!!)





            if (guess!!.length < 5 || guess!!.length>5) {

                Toast.makeText(context, "Please write a 5 letter word", Toast.LENGTH_SHORT).show()
            }
            else {

                guessNumber--
                binding.guessNumberText.text="Guess:" +guessNumber.toString()


                if (guess!!.equals(word!!)){

                    val transition= GuessPageFragmentDirections.guessPageToResult("WIN")
                    findNavController().navigate(transition)
                }


                else if (guessNumber==0){

                    val transition= GuessPageFragmentDirections.guessPageToResult(word!!.toString())
                    findNavController().navigate(transition)
                }

                for (i in 0..4) {

                    for (y in 0..4) {

                        if (guess!![i] == word!![i]) {

                            greenContainList.add(word!![i])
                        }

                       else if (guess!![i] == word!![y] && i!=y){

                            yellowContainList.add(word!![y])
                        }
                    }
                }

              //  binding.guessNumberText.text = word
             //   binding.hintText.text = containList.toString()

                for (i in 0..4) {

                    if (greenContainList.contains(word!![i])){

                        binding.hintText.text=binding.hintText.text as String + word!![i].toUpperCase()
                    }

                   else if (yellowContainList.contains(guess!![i]) && !greenContainList.contains(guess!![i]))
                        binding.hintText.text=binding.hintText.text as String + guess!![i]

                    else{

                        binding.hintText.text=binding.hintText.text as String + "*"
                    }
                }
                greenContainList.clear()
                yellowContainList.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}