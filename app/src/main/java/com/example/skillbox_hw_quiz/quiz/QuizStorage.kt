package com.example.skillbox_hw_quiz.quiz

object QuizStorage {

    enum class Locale {
        Ru, En
    }

    fun getQuiz(locale: Locale): Quiz = when (locale) {
        Locale.Ru -> quizRu
        Locale.En -> quizEn
    }

    fun answer(quiz: Quiz, answers: List<Int>): String = quiz
        .questions
        .zip(answers) { question, index -> question.feedback[index] }
        .joinToString(separator = " ")

    private val quizRu = object : Quiz {
        override val questions: List<Question> = listOf(
            Question(
                question = "Как тебе мое Android приложение?",
                answers = listOf(
                    "на 2",
                    "на 3",
                    "на 4",
                    "на 5",
                ),
                feedback = listOf(
                    "Вам не нравится приложение.",
                    "Вас удовлетворяет приложением.",
                    "Вы оценили приложение на хорошо.",
                    "Вы оценили приложение на отлично.",
                ),
            ),
            Question(
                question = "Порекомендуешь его друзьям?",
                answers = listOf(
                    "Обзательно",
                    "Уже порекомендовал",
                    "Может быть",
                    "Нет",
                ),
                feedback = listOf(
                    "Вы готовы порекомендовать данное приложение.",
                    "Вы уже порекомендовали приложение своим друзьям.",
                    "Вам нужно больше времени, чтобы порекомендовать данное приложение.",
                    "Вы не готовы рекомендовать данное приложение.",
                ),
            ),
            Question(
                question = "Есть ли у вас идеии как можно улучшить данное приложение?",
                answers = listOf(
                    "Да",
                    "Еще нет, но я подумаю",
                    "Нет, вроде все хорошо",
                    "Нет, все идеально",
                ),
                feedback = listOf(
                    "Спасибо за обратную связь!",
                    "Спасибо за обратную связь!",
                    "Спасибо за высокую оценку!",
                    "Спасибо за высокую оценку",
                ),
            ),
        )
    }


    private val quizEn = object : Quiz {
        override val questions: List<Question> = listOf(
            Question(
                question = "Please, Rate my Android App?",
                answers = listOf(
                    "F",
                    "C",
                    "B",
                    "A++",
                ),
                feedback = listOf(
                    "You have rated us F",
                    "You have rated us C",
                    "You have rated us B",
                    "You have rated us A++",
                ),
            ),
            Question(
                question = "Do you want to recommend this app to your friends?",
                answers = listOf(
                    "Sure",
                    "Already recommend",
                    "I don't know",
                    "No",
                ),
                feedback = listOf(
                    "You want to recommend this app",
                    "You already recommend this app",
                    "You need more time for make recommendations",
                    "You are not ready to recommendations",
                ),
            ),
            Question(
                question = "Do you have any ideas how this application can be improved?",
                answers = listOf(
                    "Yes",
                    "Not yet, but I'll think about it ",
                    "No, everything seems to be fine",
                    "No, everything is fine",
                ),
                feedback = listOf(
                    "Thanks for feedback!",
                    "Thanks for feedback!",
                    "Thanks for the high rating!",
                    "Thanks for the high rating",
                ),
            ),
        )
    }
}