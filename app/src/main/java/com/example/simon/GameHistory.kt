package com.example.simon

import androidx.lifecycle.ViewModel
import kotlin.collections.MutableList

//The GameHistory class allows for the storing of matches through the use of two mutable lists.
//The class extends ViewModel() in order to preserve the instance state
class GameHistory(): ViewModel(){

    //This list stores the sequences
    private var gameSequenceHistory = MutableList(0){""}

    //This list stores the number of pressed buttons
    private var gameCountHistory = MutableList(0){0}

    //Creates a Match object in order to get the current count and sequence
    var currentMatch = Match()

    //Getter function for the sequence
    fun getGameSequenceHistory(): MutableList<String>{
        return gameSequenceHistory
    }

    //Getter function for the count
    fun getGameCountHistory(): MutableList<Int>{
        return gameCountHistory
    }

    //This function stores the current count and sequence in the history and resets them
    fun endGame(){
        gameSequenceHistory.add(currentMatch.getSequence())
        gameCountHistory.add(currentMatch.getCount())
        currentMatch.resetCount()
        currentMatch.resetSequence()
    }


}



