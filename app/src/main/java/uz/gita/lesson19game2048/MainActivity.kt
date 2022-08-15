package uz.gita.lesson19game2048

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.gita.lesson19game2048.contract.presenter.GamePresenterImpl
import uz.gita.lesson19game2048.contract.repository.GameRepositoryImpl
import uz.gita.lesson19game2048.contract.screenContract.GameContract
import uz.gita.lesson19game2048.data.Movement
import uz.gita.lesson19game2048.shared.MyShared
import uz.gita.lesson19game2048.utils.MovementDetector

class MainActivity : AppCompatActivity(), GameContract.View {
    private val buttons: ArrayList<TextView> = ArrayList(16)
    private val presenter = GamePresenterImpl(this, GameRepositoryImpl())
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var myShared: MyShared
    private var isMuted = true
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "2048 GAME"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1B0B25")))

        loadViews()
        presenter.startGame()

    }

    override fun onResume() {
        super.onResume()

        mediaPlayer = MediaPlayer()
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_background)

        myShared = MyShared.getShared()

        myShared.getNumberList()

        score = myShared.getScore()
        isMuted = myShared.getIsMuted()

        setScore(score)
        check(isMuted)
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun loadViews() {
        textView = findViewById(R.id.btn_score)

        imageView = findViewById(R.id.btn_music)
        imageView.setOnClickListener {
            check(isMuted)
        }

        val mainContainer = findViewById<LinearLayout>(R.id.mainContainer)

        for (i in 0 until mainContainer.childCount) {
            val childContainer = mainContainer.getChildAt(i) as LinearLayout
            for (j in 0 until childContainer.childCount) {
                buttons.add(childContainer.getChildAt(j) as TextView)
            }
        }

        val movementDetector = MovementDetector(this)

        movementDetector.setOnMovementListener {
            when (it) {
                Movement.LEFT -> presenter.moveLeft()
                Movement.RIGHT -> presenter.moveRight()
                Movement.DOWN -> presenter.moveDown()
                Movement.UP -> presenter.moveUp()
            }
        }
        mainContainer.setOnTouchListener(movementDetector)
    }


    override fun changeState(matrix: Array<Array<Int>>) {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                val button = buttons[4 * i + j]
                val value = matrix[i][j]
                if (value == 0) button.text = ""
                else button.text = matrix[i][j].toString()
                button.setBackgroundColor(
                    when (value) {
                        2 -> Color.parseColor("#B1CD44")
                        4 -> Color.parseColor("#78BA3F")
                        8 -> Color.parseColor("#3AB074")
                        16 -> Color.parseColor("#2DA8E1")
                        32 -> Color.parseColor("#3466AF")
                        64 -> Color.parseColor("#514597")
                        128 -> Color.parseColor("#7D4294")
                        256 -> Color.parseColor("#C22F8A")
                        512 -> Color.parseColor("#DC2E4F")
                        1024 -> Color.parseColor("#E04A32")
                        2048 -> Color.parseColor("#E86225")
                        else -> Color.parseColor("#662A8C")
                    }
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
        writeShared(presenter.getScore())
        presenter.writeNumbers()
    }

    override fun writeShared(score: Int) {
        myShared.setScore(score)
        myShared.setNumberList(presenter.writeNumbers())
        myShared.setIsMuted(!isMuted)
    }

    @SuppressLint("SetTextI18n")
    override fun setScore(score: Int) {
        textView.text = "Score: $score"
    }

    private fun check(check: Boolean) {
        isMuted = if (check) {
            imageView.setImageResource(R.drawable.ic_baseline_music_note_24)
            mediaPlayer.start()
            false
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_music_off_24)
            mediaPlayer.pause()
            true
        }
    }
}

