package uz.gita.lesson19game2048

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        title = "2048 GAME"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1B0B25")))


        findViewById<TextView>(R.id.play_btn).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }
        findViewById<TextView>(R.id.tv_scores).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    InfoActivity::class.java
                )
            )
        }

    }
}