package uz.gita.lesson19game2048.shared

import android.content.Context
import android.content.SharedPreferences

class MyShared private constructor() {
    companion object {
        private lateinit var myShared: MyShared
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun getInstance(context: Context): MyShared {
            if (!::myShared.isInitialized) {
                myShared = MyShared()
                sharedPreferences = context.getSharedPreferences("My_app", Context.MODE_PRIVATE)
                editor = sharedPreferences.edit()
            }
            return myShared
        }

        fun getShared() = myShared
    }

    fun setScore(score: Int) {
        editor.putInt("score", score).apply()
    }

    fun getScore(): Int {
        return sharedPreferences.getInt("score", 0)
    }

    fun setIsMuted(isMuted: Boolean) {
        editor.putBoolean("isMuted", isMuted).apply()
    }

    fun getIsMuted(): Boolean {
        return sharedPreferences.getBoolean("isMuted", true)
    }

    fun setRecord(list: String) {
        editor.putString("record", list).apply()
    }

    fun getRecords(): String {
        return sharedPreferences.getString("record", "0")!!
    }

    fun setNumberList(list: String) {
        editor.putString("numbers", list).apply()
    }

    fun getNumberList(): String {
        return sharedPreferences.getString("numbers", "0#0#0#0#0#0#0#0#0#0#0#0#0#0#0#0")!!
    }
}