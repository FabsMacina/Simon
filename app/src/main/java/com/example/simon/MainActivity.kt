package com.example.simon

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
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
    val orientation = LocalConfiguration.current.orientation

    ConstraintLayout(modifier = modifier) {
        val (c1, c2, r1, r2) = createRefs()
        Row(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.1f)
        }else{
            Modifier.fillMaxHeight(0.05f)
        }.fillMaxWidth().constrainAs(r1) {
            top.linkTo(parent.top)
            bottom.linkTo(c1.top)
            start.linkTo(parent.start)
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
        Column(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.95f).fillMaxWidth(0.5f)
            }else{
            Modifier.fillMaxHeight(0.5f).fillMaxWidth()
        }
            .constrainAs(c1) {
            top.linkTo(r1.bottom, margin = 0.dp)
            start.linkTo(parent.start)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                bottom.linkTo(parent.bottom)
                end.linkTo(c2.start)
            }else{
                bottom.linkTo(c2.top)
                end.linkTo(parent.end)
            }
        },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Row(modifier = Modifier.weight(1f).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(top = 0.dp),
                    colors = ButtonDefaults.buttonColors(Red),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "R, " }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(top = 0.dp),
                    colors = ButtonDefaults.buttonColors(Green),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "G, " }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Blue),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "B, " }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Magenta),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "M, " }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Yellow),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "Y, " }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(Cyan),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { buttonCounter += "C, " }
                ) {}
            }
        }
        Column(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.7f).fillMaxWidth(0.5f)
        }else{
            Modifier.fillMaxHeight(0.35f).fillMaxWidth()
        }.padding(0.dp).constrainAs(c2){
            end.linkTo(parent.end)
            bottom.linkTo(r2.top)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                top.linkTo(r1.bottom)
                start.linkTo(c1.end)
            }else{
                top.linkTo(c1.bottom)
                start.linkTo(parent.start)
            }
        }  ){
            Text(
                text = buttonCounter,
                modifier = modifier.verticalScroll(scrollState),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Row(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.2f).fillMaxWidth(0.5f)
        }else{
            Modifier.fillMaxHeight(0.1f).fillMaxWidth()
        }.padding(0.dp).constrainAs(r2){
            top.linkTo(c2.bottom)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                start.linkTo(c1.end)
            }else{
                start.linkTo(parent.start)
            }
        } ){
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(Red2),
                contentPadding = PaddingValues(0.dp),
                onClick = {buttonCounter = ""}
            ) {
                Text(text = stringResource(R.string.canc),
                    modifier = modifier)
            }
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(Green2),
                contentPadding = PaddingValues(0.dp),
                onClick = {buttonCounter = ""}
            ) {
                Text(text = stringResource(R.string.end),
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