package com.example.simon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun HistoryScreen(){
    ConstraintLayout(modifier = Modifier) {
        Text("PROVA")
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview(){
    HistoryScreen()
}