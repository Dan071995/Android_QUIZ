package com.example.skillbox_hw_quiz.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentMainActionBinding


class MainActionFragment : Fragment() {

    private var _binding: FragmentMainActionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Подключам разметку к фрагменту
        _binding = FragmentMainActionBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        //Программно добавляем в MainActionFragment контейнеры questionBlank:
        val context = activity?.applicationContext
        val questionBlank1 = QuestionBlank(context!!)
        questionBlank1.fullBlank(1)

        binding.linearLayout.addView(questionBlank1)
        */

        //Создаем 3 бланка
        val blank1 = binding.blank1
        val blank2 = binding.blank2
        val blank3 = binding.blank3
        //Заполняем их вопросами и вариантами ответа из класса QuizStorage, и получаем сам вопрос
        val question1 = blank1.fullBlank(1)
        val question2 = blank2.fullBlank(2)
        val question3 = blank3.fullBlank(3)

        ///////////////////////////////////////////////////////////////////////////////////////////

        //Анимируем плавное и последовательное появление бланокв с вопросами друг за другом:
        blank1.alpha = 0f
        blank2.alpha = 0f
        blank3.alpha = 0f

        val blank1Animation = ObjectAnimator.ofFloat(blank1,View.ALPHA,0f,1f).apply {
            duration = 600
        }
        val blank2Animation = ObjectAnimator.ofFloat(blank2,View.ALPHA,0f,1f).apply {
            duration = 500
        }
        val blank3Animation = ObjectAnimator.ofFloat(blank3,View.ALPHA,0f,1f).apply {
            duration = 500
        }

        AnimatorSet().apply {
            play(blank2Animation).after(blank1Animation)
            play(blank3Animation).after(blank2Animation)
            start()
        }

        ///////////////////////////////////////////////////////////////////////////////////////////

        //По нажатию кнопки, получаем информацию из бланков (feedback) и передаем ее в следующий фрагмент
        binding.toResultsButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("question1",question1)
                putString("question2",question2)
                putString("question3",question3)

                putString("answer1",blank1.takeResults())
                putString("answer2",blank2.takeResults())
                putString("answer3",blank3.takeResults())
            }
            findNavController().navigate(R.id.mainActionFragment_to_resultsFragment,bundle)
        }

        //По нажатию кнопки возвращаемся на WelcomeScreen
        binding.toWelcomeScreenButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainActionFragment_to_welcomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}