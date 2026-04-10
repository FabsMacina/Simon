package com.example.simon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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

var a: Int = 0

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    ConstraintLayout(modifier = modifier) {
        val (b1, b2, b3, b4, b5, b6) = createRefs()

        Button(
            modifier = Modifier.constrainAs(b1){
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 4.dp)
            },
            onClick = {}
        ){}
        Button(
            modifier = Modifier.constrainAs(b2){
                top.linkTo(parent.top)
                start.linkTo(parent.end, margin = 4.dp)
            },
            onClick = {}
        ){}
        Button(
            modifier = Modifier.constrainAs(b3){
                top.linkTo(b1.bottom)
                start.linkTo(parent.start, margin = 4.dp)
            },
            onClick = {}
        ){}
        Button(
            modifier = Modifier.constrainAs(b4){
                top.linkTo(b2.bottom)
                start.linkTo(parent.end, margin = 4.dp)
            },
            onClick = {}
        ){}
        Button(
            modifier = Modifier.constrainAs(b5){
                top.linkTo(b3.bottom)
                start.linkTo(parent.start, margin = 4.dp)
            },
            onClick = {}
        ){}
        Button(
            modifier = Modifier.constrainAs(b6){
                top.linkTo(b4.bottom)
                start.linkTo(parent.end, margin = 4.dp)
            },
            onClick = {}
        ){}

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}