package com.manshal_khatri.response

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class GameOver : AppCompatActivity() {

    lateinit var scoreVal : TextView
    lateinit var scoreHead : TextView
    lateinit var topPlayer : EditText
    lateinit var new : TextView
    lateinit var homebtn : Button
    lateinit var playAgainbtn : Button
    lateinit var submit : Button

    lateinit var sharedPreferences : SharedPreferences
    var scoreIs = 0
    var highScore=scoreIs



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        new=findViewById(R.id.newText)
        scoreHead=findViewById(R.id.scoreHead)
        topPlayer=findViewById(R.id.topPlayer)
        submit=findViewById(R.id.submit)

        val mode=intent.getIntExtra("mode",1)
        sharedPreferences = getSharedPreferences(getString(R.string.highScore), MODE_PRIVATE)
        highScore=sharedPreferences.getInt("highScore$mode",scoreIs)

        scoreVal=findViewById(R.id.scoreVal)
        homebtn=findViewById(R.id.homeBtn)
        playAgainbtn=findViewById(R.id.playAgain)



        scoreIs=intent.getIntExtra("score",0)
        scoreVal.text=scoreIs.toString()

        if(highScore<scoreIs){
            sharedPreferences.edit().putInt("highScore$mode",scoreIs).apply()
            new.text="New"
            scoreHead.text="HighScore"
            topPlayer.visibility=0
            if(topPlayer.text.isNotEmpty()){
                submit.visibility=0
            }else{
                submit.visibility=1
            }

        }
        homebtn.setOnClickListener {
            finish()                            // should be handled efficiently try to know how ?
        }
        playAgainbtn.setOnClickListener {
            val intent : Intent
            if(mode == 5) {
                 intent = Intent(this@GameOver,RapidFirePS::class.java)
            }else {
                 intent = Intent(this@GameOver, PlayScreen::class.java)
                intent.putExtra("mode", mode)
            }
            startActivity(intent)
            finish()                            // should be handled efficiently try to know how ?
        }
        submit.setOnClickListener {
            sharedPreferences.edit().putString("topPlayer$mode",topPlayer.text.toString()).apply()

            Toast.makeText(this@GameOver, "Score submitted", Toast.LENGTH_SHORT).show()
            topPlayer.visibility=1
            submit.visibility=1
            finish()
        }

    }

}