package com.example.simon

//The Match class stores information about the current played sequence
class Match(){

    //The variable count stores the number of buttons pressed
    private var count: Int = 0

    //The variable sequence stores the sequence of buttons pressed
    private var sequence: String = ""

    //Once a button is pressed, the respective letter is added in the sequence, followed by a comma
    //and a space. The counter is updated as well.
    fun buttonPress(color: Char): String {
        if(sequence != "")
            sequence += ", "
        sequence += color
        count ++
        return sequence
    }

    //Getter function for the count
    fun getCount(): Int{
        return count
    }

    //Getter function for the sequence
    fun getSequence(): String{
        return sequence
    }

    //resets the count
    fun resetCount(){
        count=0
    }

    //resets the sequence
    fun resetSequence(){
        sequence = ""
    }
}
