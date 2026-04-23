package com.example.simon

fun buttonPress(color: Char, count: String): String {
    var c: String = count
    if(c != "")
        c += ", "
    c += color
    return c
}