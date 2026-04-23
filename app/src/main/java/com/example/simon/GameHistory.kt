package com.example.simon

import kotlin.collections.MutableList

public class GameHistory(){

    private var gameHistory = MutableList(0){""}

    fun getGameHistory(): MutableList<String>{
        return gameHistory
    }

    fun endGame(count: String){
        gameHistory.add(count)
    }


}



