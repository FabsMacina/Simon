package com.example.simon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import com.example.simon.ui.theme.BuBlue
import com.example.simon.ui.theme.BuCyan
import com.example.simon.ui.theme.BuGreen
import com.example.simon.ui.theme.BuMagenta
import com.example.simon.ui.theme.BuRed
import com.example.simon.ui.theme.BuYellow
import com.example.simon.ui.theme.SimonTheme


class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    ConstraintLayout(modifier = modifier) {
        val (c1) = createRefs()
        Column(modifier = modifier.constrainAs(c1) {},
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuRed,
                    onClick = {}
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuGreen,
                    onClick = {}
                ) {}
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuBlue,
                    onClick = {}
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuMagenta,
                    onClick = {}
                ) {}
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuYellow,
                    onClick = {}
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = BuCyan,
                    onClick = {}
                ) {}
            }
        }
        Row(modifier = modifier){

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}