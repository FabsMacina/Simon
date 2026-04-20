package com.example.simon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.simon.ui.theme.Green2
import com.example.simon.ui.theme.Magenta
import com.example.simon.ui.theme.Red
import com.example.simon.ui.theme.Red2
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
    val scrollState = rememberScrollState()
    ConstraintLayout(modifier = modifier) {
        val (c1, c2, r1, r2) = createRefs()
        Row(modifier = Modifier.fillMaxHeight(0.05f).constrainAs(r1) {
            top.linkTo(parent.top)
            bottom.linkTo(c1.top)
            end.linkTo(parent.end)
        }, horizontalArrangement = Arrangement.End){
            Button(
                modifier = Modifier.fillMaxHeight(),
                shape = ButtonDefaults.shape,
                contentPadding = PaddingValues(0.dp),
                onClick = {}
            ) {
                Text(modifier = Modifier.padding(horizontal = 8.dp),
                    text = "English",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Column(modifier = Modifier.fillMaxHeight(0.5f).constrainAs(c1) {
            top.linkTo(r1.bottom, margin = 0.dp)
        },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Row(modifier = Modifier.weight(1f).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight().padding(top = 0.dp),
                    colors = ButtonDefaults.buttonColors(Red),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "R, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight().padding(top = 0.dp),
                    colors = ButtonDefaults.buttonColors(Green),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "G, " }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Blue),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "B, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Magenta),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "M, " }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Yellow),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "Y, " }
                ) {}
                Button(
                    modifier = modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Cyan),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "C, " }
                ) {}
            }
        }
        Column(modifier = Modifier.padding(0.dp).fillMaxHeight(0.2f).constrainAs(c2){
            top.linkTo(c1.bottom)
            }  ){
            Text(
                text = buttonCounter,
                modifier = modifier.verticalScroll(scrollState),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Row(modifier = Modifier.padding(0.dp).fillMaxHeight(0.1f).constrainAs(r2){
            top.linkTo(c2.bottom)
        } ){
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(Red2),
                contentPadding = PaddingValues(0.dp),
                onClick = {}
            ) {
                Text(text = "Cancel",
                    modifier = modifier)
            }
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(Green2),
                contentPadding = PaddingValues(0.dp),
                onClick = {}
            ) {
                Text(text = "End match",
                    modifier = modifier)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}