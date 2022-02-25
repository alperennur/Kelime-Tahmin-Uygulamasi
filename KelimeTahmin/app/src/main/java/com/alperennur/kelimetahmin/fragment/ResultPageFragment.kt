package com.alperennur.kelimetahmin.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.alperennur.kelimetahmin.R
import com.alperennur.kelimetahmin.databinding.FragmentResultPageBinding


class ResultPageFragment : Fragment() {

    private var _binding: FragmentResultPageBinding?=null
    private val binding get() = _binding!!
    private val args: ResultPageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentResultPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = args.result
        if (result=="WIN"){

            binding.resulText.setText(result)
            binding.wordText.isVisible=false
            binding.resultImage.setImageResource(R.drawable.win)
        }else{

            binding.resulText.setTextColor(Color.RED)
            binding.resulText.setText("LOSE")
            binding.wordText.setText("WORD:$result")
            binding.resultImage.setImageResource(R.drawable.lose)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}