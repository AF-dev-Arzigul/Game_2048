package uz.gita.lesson19game2048

import android.app.Application
import uz.gita.lesson19game2048.shared.MyShared

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MyShared.getInstance(this)
    }
}