package uz.gita.lesson19game2048.contract.presenter

import uz.gita.lesson19game2048.contract.screenContract.GameContract

class GamePresenterImpl(
    private val view: GameContract.View,
    private val repository: GameContract.Repository
) : GameContract.Presenter {
    override fun startGame() {
        view.changeState(repository.getMatrix())
    }

    override fun moveLeft() {
        repository.moveLeft()
        view.setScore(repository.getScore())
        view.changeState(repository.getMatrix())
    }

    override fun moveRight() {
        repository.moveRight()
        view.setScore(repository.getScore())
        view.changeState(repository.getMatrix())
    }

    override fun moveDown() {
        repository.moveDown()
        view.setScore(repository.getScore())
        view.changeState(repository.getMatrix())
    }

    override fun moveUp() {
        repository.moveUp()
        view.setScore(repository.getScore())
        view.changeState(repository.getMatrix())
    }

    override fun getScore(): Int {
        return repository.getScore()
    }

    override fun getMatrix(): Array<Array<Int>> {
        return repository.getMatrix()
    }

    override fun writeNumbers():String {
        return repository.writeNumbers()
    }

    override fun loadNumbers(list: String) {
        repository.loadNumbers(list)
    }
}