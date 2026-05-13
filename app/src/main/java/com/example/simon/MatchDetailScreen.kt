package com.example.simon

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.text.take

@Composable
fun MatchDetailScreen(sequence: String, count: Int){

    //Required for different configurations for landscape and portrait orientations
    val orientation = LocalConfiguration.current.orientation

    //Enables constraint layout, allowing the elements to anchor to one another
    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
        val (c1, r1, card) = createRefs()

        //This row contains the card in the upper right corner displaying the current language
        Row(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.1f)
        }else{
            Modifier.fillMaxHeight(0.05f)
        }.fillMaxWidth().constrainAs(r1) {
            top.linkTo(parent.top)
            bottom.linkTo(card.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, horizontalArrangement = Arrangement.End){
            Card(
                modifier = Modifier.fillMaxHeight().align(Alignment.CenterVertically),
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

        //This row contains a box that displays the title of the screen
        Row(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.2f)
        }else{
            Modifier.fillMaxHeight(0.1f)
        }
            .constrainAs(card){
                top.linkTo(r1.bottom)
                bottom.linkTo(c1.top)
                start.linkTo(parent.start)
            }){
            Box(modifier = Modifier.fillMaxHeight()){
                Text(modifier = Modifier.padding(horizontal = 8.dp).align(Alignment.Center),
                    text = stringResource(R.string.det_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        //This column contains the list of played matches, a row for each match.
        LazyColumn(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.65f)
        }else{
            Modifier.fillMaxHeight(0.8f)
        }
            .fillMaxWidth()
            .constrainAs(c1){
                top.linkTo(card.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            } ) {
            item{
                Row(modifier = Modifier.padding(10.dp).fillMaxWidth()){

                    //These boxes contain the number of buttons pressed in the sequence
                    Box(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                        Modifier.fillMaxWidth(0.05f)
                    }else{
                        Modifier.fillMaxWidth(0.1f)
                    } ){
                        Text(text = "$count",
                            modifier = Modifier,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium)
                    }

                    //These boxes contain the sequence of buttons pressed
                    Box(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                        Modifier.fillMaxWidth(0.95f)
                    }else{
                        Modifier.fillMaxWidth(0.9f)
                    } ){
                        Text(text = sequence.ifEmpty { "" },
                            modifier = Modifier,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}