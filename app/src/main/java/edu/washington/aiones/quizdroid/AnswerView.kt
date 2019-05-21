package edu.washington.aiones.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class AnswerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_view)


        val receivedIntent = intent
        val myQuestions = receivedIntent.extras.getSerializable("FromMain") as? QuestionSet
        val currentQuestionIndex = receivedIntent.getIntExtra("QuestionNumber", 0)
        var currentCorrect = receivedIntent.getIntExtra("CurrentCorrect",0)
        val givenAnswerIndex = receivedIntent.getIntExtra("GivenAnswerIndex",-1)


        val currentQuestion = myQuestions!!.questions!!.entries.elementAt(currentQuestionIndex)

        val givenAnswer = currentQuestion.value[givenAnswerIndex]
        val correctAnswerIndex = myQuestions!!.answerIndex!![currentQuestionIndex]
        val correctAnswer = currentQuestion.value[correctAnswerIndex]

        val givenAnswerString = "You gave the answer: $givenAnswer"
        val correctAnswerString = "The correct answer is: $correctAnswer"
        val totalCorrectString = "You have $currentCorrect out of ${myQuestions!!.questions!!.keys.size}."

        findViewById<TextView>(R.id.givenAnswer).text = givenAnswerString
        findViewById<TextView>(R.id.correctAnswer).text = correctAnswerString
        findViewById<TextView>(R.id.totalCorrect).text = totalCorrectString

        val nextButton = findViewById<Button>(R.id.nextButton)

        // if last question has been answered, take back to beginning
        if (currentQuestionIndex == myQuestions!!.questions!!.size - 1) {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
        // otherwise, next question
        } else {
            nextButton.setOnClickListener {
                val intentToSend = Intent(this, QuestionView::class.java)
                intentToSend.putExtra("FromMain", myQuestions)
                intentToSend.putExtra("QuestionNumber",currentQuestionIndex + 1)
                intentToSend.putExtra("CurrentCorrect", currentCorrect)
                startActivity(intentToSend)
            }
        }



    }
}
