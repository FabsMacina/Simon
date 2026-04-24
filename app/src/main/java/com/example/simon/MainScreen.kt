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
//The main screen requires a GameHistory object and onEndClicked().
//It displays a screen containing a card that shows the current language, a 3x2 matrix of buttons,
//a card that shows the sequence of pressed buttons, the cancel and end game buttons.
fun MainScreen(history: GameHistory, onEndClicked: () -> Unit){

    //This allows to remember the instance state of currentSequence
    var currentSequence by rememberSaveable { mutableStateOf(history.currentMatch.getSequence()) }

    //Allows the displayed sequence of pressed buttons to scroll vertically
    val scrollState = rememberScrollState()

    //Required for different configurations for landscape and portrait orientations
    val orientation = LocalConfiguration.current.orientation

    //Enables constraint layout, allowing the elements to anchor to one another
    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
        val (c1, c2, r1, r2) = createRefs()

        //This row contains the card in the upper right corner displaying the current language
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

        //This column contains the rows that contain the main buttons, allowing for a 3x2 matrix display
        //The different heights, widths and anchoring allow for a different display in landscape mode
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

            //All the rows and buttons have a weight of 1 to allow the correct displaying
            Row(modifier = Modifier.weight(1f).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

                //All button colors are implemented in ui.theme.Colors.kt
                //onClick calls the buttonPress function from the Match class
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

        //This column contains a card that displays the sequence of buttons pressed so far
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

        //This row contains the "Cancel" and "End Game" buttons
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

            //onClick resets the current sequence and counter, both in the match object and displayed
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

            //onClick calls the endGame() function, resets the displayed sequence and calls onEndClicked()
            //The onEndClicked() function allows the Nav to switch to the next screen
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