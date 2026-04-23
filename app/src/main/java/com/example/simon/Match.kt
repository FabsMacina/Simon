package com.example.simon

class Match(){
    private var count: Int = 0
    private var sequence: String = ""

    fun buttonPress(color: Char): String {
        if(sequence != "")
            sequence += ", "
        sequence += color
        count ++
        return sequence
    }

    fun getCount(): Int{
        return count
    }

    fun getSequence(): String{
        return sequence
    }
    fun resetCount(){
        count=0
    }
    fun resetSequence(){
        sequence = ""
    }
}
