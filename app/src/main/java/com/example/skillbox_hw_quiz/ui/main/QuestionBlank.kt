package com.example.skillbox_hw_quiz.ui.main

import android.content.Context
import android.content.res.Resources
import android.icu.util.ULocale.getLanguage
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.QuestionBlankBinding
import com.example.skillbox_hw_quiz.quiz.Question
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.Locale

class QuestionBlank
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int =  0
): CardView(context, attrs, defStyleAttr){
    //свяжем нашу кастомную группу (верстку) с кодом
    private val binding = QuestionBlankBinding.inflate(LayoutInflater.from(context))
    private lateinit var questionList : Question

    //В блоке Init добавим верстку с помощью метода addView. addView добавит внутрь class QuestionBlank
    //верстку xml, для этого используется метод binding.root
    init {
        addView(binding.root) //root ЭТО САМА ВЕРСТКА!
    }
    //новый компнент (ViewGroup), представляет из себя новый экран xml. данный экран не наследуется от : AppCompatActivity()
    //в отличае от MainActivity. поэтому binding активируется здесь немного другим способом -> передается контекст нашего класса
    //а сама верстка добавляется через addView(binding.root) а не setContentView(binding.root) как в MainActivity.

    //Далее опишим методы, для изменения нашего бланка:
    fun fullBlank(questionNumber:Int):String{

        //Получаем список, хранящий экземпляры класса Question.
        //В этом классе есть:
        //строковая переменная .question (наш вопрос)
        //есть список с вариантами ответа на этот вопрос (.answers)
        //и есть список с реакциями на ответ (.feedback)

        //Будем возвращать вопрос
        var question = resources.getText(R.string.errorQuestion).toString()

        //Если передаем не верное значение:
        if (questionNumber <= 0) {
            binding.apply {
                question1.setText(R.string.errorQuestion)
                rB1.setText(R.string.error)
                rB2.setText(R.string.error)
                rB3.setText(R.string.error)
                rB4.setText(R.string.error)
            }
            return question
        }

        //получаем экземпляр класса Question, соответствующий вопросу номер: questionNumber:Int
        //В зависимости от Локализации (RU EN) будет возвращаться локализованный экземпляр этого класса
        if(Locale.getDefault().language == "en") questionList = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[questionNumber - 1]
        else questionList = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[questionNumber - 1]
        //Заполним карточку:
        binding.apply {
            question = questionList.question
            question1.text = questionList.question //Устанваливаем текст вопроса
            rB1.text = questionList.answers[0]     //Устанавливаем варианты ответа
            rB2.text = questionList.answers[1]
            rB3.text = questionList.answers[2]
            rB4.text = questionList.answers[3]
        }
        return question
    }
    //Данный метод возвращет feedback в зависимости от состояния RadioButton
    fun takeResults():String{
        var result:String = resources.getText(R.string.answer_not_selected).toString() //Переменная, хранящая результат в зависимости от выбраннго ответа

        binding.apply {
            when(true){
                rB1.isChecked -> result = questionList.feedback[0]
                rB2.isChecked -> result = questionList.feedback[1]
                rB3.isChecked -> result = questionList.feedback[2]
                rB4.isChecked -> result = questionList.feedback[3]
            }
        }
        return result
    }

}