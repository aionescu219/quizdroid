package edu.washington.aiones.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_view)

        val submitButton = findViewById<Button>(R.id.submitAnswer)
        submitButton.isEnabled = false

        val receivedIntent = intent
        val myQuestions = receivedIntent.extras.getSerializable("FromMain") as? QuestionSet
        val currentQuestionIndex = receivedIntent.getIntExtra("QuestionNumber", 0)
        var currentCorrect = receivedIntent.getIntExtra("CurrentCorrect",0)


        val currentQuestion = myQuestions!!.questions!!.entries.elementAt(currentQuestionIndex)
        val questionText : String = currentQuestion.key
        val answersText : Array<String> = currentQuestion.value

        val correctAnswerIndex = myQuestions!!.answerIndex!![currentQuestionIndex]

        findViewById<TextView>(R.id.questionText).text = questionText


        findViewById<RadioButton>(R.id.answer1).text = answersText[0]
        findViewById<RadioButton>(R.id.answer2).text = answersText[1]
        findViewById<RadioButton>(R.id.answer3).text = answersText[2]
        findViewById<RadioButton>(R.id.answer4).text = answersText[3]

        val radioButtons = findViewById<RadioGroup>(R.id.answer_group)

        findViewById<Button>(R.id.submitAnswer).setOnClickListener {

            val answerIndex = when(radioButtons.checkedRadioButtonId) {
                R.id.answer1 -> 0
                R.id.answer2 -> 1
                R.id.answer3 -> 2
                R.id.answer4 -> 3
                else -> null
            }

            if (correctAnswerIndex == answerIndex) {
                currentCorrect++
            }

            val intentToSend = Intent(this, AnswerView::class.java)
            intentToSend.putExtra("FromMain", myQuestions)
            intentToSend.putExtra("QuestionNumber", currentQuestionIndex)
            intentToSend.putExtra("CurrentCorrect", currentCorrect)
            intentToSend.putExtra("GivenAnswerIndex", answerIndex)
            startActivity(intentToSend)

        }


    }

    fun onRadioButtonClicked(view : View) {
        val submitButton = findViewById<Button>(R.id.submitAnswer)
        submitButton.isEnabled = true
    }
}
