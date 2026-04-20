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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simon.ui.theme.Blue
import com.example.simon.ui.theme.Cyan
import com.example.simon.ui.theme.Green
import com.example.simon.ui.theme.Magenta
import com.example.simon.ui.theme.Red
import com.example.simon.ui.theme.Yellow
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
    var buttonCounter by rememberSaveable { mutableStateOf("") }
    ConstraintLayout(modifier = modifier) {
        val (c1, r1) = createRefs()
        Column(modifier = modifier.constrainAs(c1) {},
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Red),
                    onClick = { buttonCounter += "R, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Green),
                    onClick = { buttonCounter += "G, " }
                ) {}
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Blue),
                    onClick = { buttonCounter += "B, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Magenta),
                    onClick = { buttonCounter += "M, " }
                ) {}
            }
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Yellow),
                    onClick = { buttonCounter += "Y, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Cyan),
                    onClick = { buttonCounter += "C, " }
                ) {}
            }
        }
        Row(modifier = modifier.padding(10.dp).constrainAs(r1){
            top.linkTo(c1.bottom)
        }){
            Text(
                text = buttonCounter,
                modifier = modifier,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}