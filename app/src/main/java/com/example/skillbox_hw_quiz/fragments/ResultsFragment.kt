package com.example.skillbox_hw_quiz.fragments

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Подключам разметку к фрагменту
        _binding = FragmentResultsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            question1.text = arguments?.getString("question1")
            question2.text = arguments?.getString("question2")
            question3.text = arguments?.getString("question3")

            result11TextView.text = arguments?.getString("answer1")
            result12TextView.text = arguments?.getString("answer2")
            result13TextView.text = arguments?.getString("answer3")

            //Анимируем появление результатов (можно передать и лейблы question1-3)
            createAlphaAnim(result11TextView,result12TextView,result13TextView)

            //добавляем Lotti фнимацию:

            backButton.setOnClickListener {
                findNavController().navigate(R.id.resultsFragment_to_mainActionFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createAlphaAnim(vararg view:TextView){
        view.forEach {
            it.alpha = 0f
            ObjectAnimator.ofFloat(it,View.ALPHA,1f).apply {
                duration = 500
                start()
            }
        }

    }

}