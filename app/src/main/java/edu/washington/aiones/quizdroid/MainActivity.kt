package edu.washington.aiones.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // changed from array of buttons to array of ids
        val buttons = arrayOf(R.id.mathButton, R.id.physicsButton, R.id.marvelButton)

        val mathQuestions =
            mapOf("What is 0!" to arrayOf("1","0","2","5"),
                "What is 0 + 1" to arrayOf("1", "0", "2", "15"),
                "What is 51 x 23" to arrayOf( "1173", "980", "1121", "5"))

        val physicsQuestions =
                mapOf("What is the freezing point of water" to arrayOf("273 Kelvin","273 Celsius", "0 Kelvin", "0 F"),
                    "At what temperature do books ignite" to arrayOf("451 F", "500 C", "1000K", "-100F"),
                    "Which of these is Newton's law of cooling" to arrayOf("none of the above", "F = MA", "E = MC^2", "aylmao"))

        val marvelQuestions = mapOf("Who is Thanos" to arrayOf("the one who is right", "spiderman's loyal sidekick", "friendly giant", "idk"),
            "What sacrifice does the Soul Stone require" to arrayOf ("a life", "money", "a joke", "none of the above"),
            "How many infinity stones are there" to arrayOf("6", "1", "5", "700"))

        val myQuestions = arrayOf(
            QuestionSet(
                buttons[0],
                "Math",
                mathQuestions,
                arrayOf(0,0,0),
                "Some questions about math. They're not very hard..."),
            QuestionSet(
                buttons[1],
                "Physics",
                physicsQuestions,
                arrayOf(0,0,0),
                "Some questions about Physics"),
            QuestionSet(
                buttons[2],
                "Marvel Super Heroes",
                marvelQuestions,
                arrayOf(0,0,0),
                "Some questions about Marvel Super Heroes"))

        for (questionSet in myQuestions) {
            findViewById<Button>(questionSet.startButtonID!!).setOnClickListener {
                val intent = Intent(this, TopicOverview::class.java)
                intent.putExtra("FromMain", questionSet)
                startActivity(intent)
            }

        }
    }
}

data class QuestionSet (
    // cannot have a button here: it is not serializable
    var startButtonID : Int? = null,
    var topicName : String? = null,
    var questions : Map<String, Array<String>>? = null,
    var answerIndex : Array<Int>? = null,
    var descriptionText : String? = null
) : Serializable


