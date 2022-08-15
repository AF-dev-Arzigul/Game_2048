package uz.gita.lesson19game2048.contract.screenContract


interface GameContract {
    interface Repository {
        fun getMatrix(): Array<Array<Int>>
        fun moveLeft()
        fun moveRight()
        fun moveDown()
        fun moveUp()
        fun getScore(): Int
        fun writeNumbers():String
        fun loadNumbers(list: String)
    }

    interface View {
        fun changeState(matrix: Array<Array<Int>>)
        fun setScore(score: Int)
        fun writeShared(score: Int)
    }

    interface Presenter {
        fun startGame()
        fun moveLeft()
        fun moveRight()
        fun moveDown()
        fun moveUp()
        fun getScore(): Int
        fun getMatrix(): Array<Array<Int>>
        fun writeNumbers():String
        fun loadNumbers(list: String)
    }
}