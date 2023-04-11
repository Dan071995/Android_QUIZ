package com.example.skillbox_hw_quiz.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Подключам разметку к фрагменту
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.welcomeFragment_to_mainActionFragment)
        }
        //Вызываем диалог с выбором даты при нажатти на кнопку "enterDate"

        val dayOfBirth = Calendar.getInstance(Locale("ru")) //Создаем календарь с русской локалью
        val formatter = SimpleDateFormat("dd-MM-yyyy") //Задаеи формат даты и времени

        binding.enterDate.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.data_picker_title)
                .build()
            //Считываем введенную пользователем дату
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                dayOfBirth.timeInMillis = timeInMillis //устанавливаем дуту рождения, которую выбрал поьзователь
                Toast.makeText(context,formatter.format(dayOfBirth.time),Toast.LENGTH_LONG).show()
            }

            dateDialog.show(parentFragmentManager,"DatePicker")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}