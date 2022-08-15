package uz.gita.lesson19game2048

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        title = "2048 GAME"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1B0B25")))


    }
}