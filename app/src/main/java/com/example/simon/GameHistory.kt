package com.example.simon

import androidx.lifecycle.ViewModel
import kotlin.collections.MutableList

class GameHistory(): ViewModel(){

    private var gameSequenceHistory = MutableList(0){""}
    private var gameCountHistory = MutableList(0){0}
    var currentMatch = Match()
    fun getGameSequenceHistory(): MutableList<String>{
        return gameSequenceHistory
    }
    fun getGameCountHistory(): MutableList<Int>{
        return gameCountHistory
    }

    fun endGame(){
        gameSequenceHistory.add(currentMatch.getSequence())
        gameCountHistory.add(currentMatch.getCount())
        currentMatch.resetCount()
        currentMatch.resetSequence()
    }


}



