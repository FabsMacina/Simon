package com.example.simon

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.simon.ui.theme.Blue
import com.example.simon.ui.theme.Cyan
import com.example.simon.ui.theme.Green
import com.example.simon.ui.theme.Green2
import com.example.simon.ui.theme.Magenta
import com.example.simon.ui.theme.Red
import com.example.simon.ui.theme.Red2
import com.example.simon.ui.theme.Yellow

@Composable
fun MainScreen(history: GameHistory, onEndClicked: () -> Unit){
    var currentSequence by rememberSaveable { mutableStateOf(history.currentMatch.getSequence()) }
    val scrollState = rememberScrollState()
    val orientation = LocalConfiguration.current.orientation

    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
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
            Card(
                modifier = Modifier.fillMaxHeight().padding().align(Alignment.CenterVertically),

            ) {
                Box(modifier = Modifier.fillMaxHeight()){
                    Text(modifier = Modifier.padding(horizontal = 8.dp).align(Alignment.Center),
                        text = stringResource(R.string.lang),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        Column(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.88f).fillMaxWidth(0.45f)
        }else{
            Modifier.fillMaxHeight(0.45f).fillMaxWidth()
        }
            .constrainAs(c1) {
                top.linkTo(r1.bottom, 10.dp)
                start.linkTo(parent.start)
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(c2.start, 5.dp)
                }else{
                    bottom.linkTo(c2.top)
                    end.linkTo(parent.end)
                }
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(modifier = Modifier.weight(1f).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(end = 2.dp),
                    colors = ButtonDefaults.buttonColors(Red),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { currentSequence = history.currentMatch.buttonPress('R') }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(start = 2.dp),
                    colors = ButtonDefaults.buttonColors(Green),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {currentSequence = history.currentMatch.buttonPress('G') }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(end = 2.dp),
                    colors = ButtonDefaults.buttonColors(Blue),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {currentSequence = history.currentMatch.buttonPress('B') }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(start = 2.dp),
                    colors = ButtonDefaults.buttonColors(Magenta),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {currentSequence = history.currentMatch.buttonPress('M') }
                ) {}
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(end = 2.dp),
                    colors = ButtonDefaults.buttonColors(Yellow),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {currentSequence = history.currentMatch.buttonPress('Y') }
                ) {}
                Button(
                    modifier = Modifier.weight(1f).fillMaxHeight().padding(start = 2.dp),
                    colors = ButtonDefaults.buttonColors(Cyan),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {currentSequence = history.currentMatch.buttonPress('C') }
                ) {}
            }
        }
        Column(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.65f).fillMaxWidth(0.45f)
        }else{
            Modifier.fillMaxHeight(0.35f).fillMaxWidth()
        }.padding(0.dp).constrainAs(c2){
            end.linkTo(parent.end)
            bottom.linkTo(r2.top)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                top.linkTo(r1.bottom, 10.dp)
                start.linkTo(c1.end, 5.dp)
            }else{
                top.linkTo(c1.bottom, 10.dp)
                start.linkTo(parent.start)
            }
        }  ){
            Card(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                Text(
                    text = currentSequence,
                    modifier = Modifier.verticalScroll(scrollState).padding(10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Row(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.2f).fillMaxWidth(0.45f)
        }else{
            Modifier.fillMaxHeight(0.1f).fillMaxWidth()
        }.padding(0.dp).constrainAs(r2){
            top.linkTo(c2.bottom, 10.dp)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                start.linkTo(c1.end, 5.dp)
            }else{
                start.linkTo(parent.start)
            }
        } ){
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(end = 2.dp),
                colors = ButtonDefaults.buttonColors(Red2),
                contentPadding = PaddingValues(0.dp),
                onClick = {history.currentMatch.resetSequence()
                    history.currentMatch.resetCount()
                    currentSequence = ""}
            ) {
                Text(text = stringResource(R.string.canc),
                    modifier = Modifier,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium)
            }
            Button(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(start = 2.dp),
                colors = ButtonDefaults.buttonColors(Green2),
                contentPadding = PaddingValues(0.dp),
                onClick = {history.endGame()
                    currentSequence = ""
                    onEndClicked()
                }
            ) {
                Text(text = stringResource(R.string.end),
                    modifier = Modifier,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium)
            }
        }
    }
}