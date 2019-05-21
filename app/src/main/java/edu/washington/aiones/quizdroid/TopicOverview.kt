package edu.washington.aiones.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_topic_overview.*

class TopicOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val receivedIntent = intent

        val myQuestions = receivedIntent.extras.getSerializable("FromMain") as? QuestionSet

        findViewById<TextView>(R.id.descriptionText).text = myQuestions!!.descriptionText

        findViewById<TextView>(R.id.numberQuestions).text = myQuestions!!.questions!!.keys.size.toString()

        val intentToSend = Intent(this, QuestionView::class.java);
        intentToSend.putExtra("FromMain", myQuestions)
        // probably unnecessary
        intentToSend.putExtra("QuestionNumber", 0)
        intentToSend.putExtra("CurrentCorrect",0)


        findViewById<Button>(R.id.beginButton).setOnClickListener{
            startActivity(intentToSend)
        }

    }
}
