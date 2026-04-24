package com.example.simon

import android.content.res.Configuration
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

@Composable
fun HistoryScreen(history: GameHistory){
    val orientation = LocalConfiguration.current.orientation

    ConstraintLayout(modifier = Modifier.padding(10.dp)) {
        val (c1, c2, r1, card) = createRefs()
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
                    text = stringResource(R.string.hist_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        LazyColumn(modifier = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.65f)
        }else{
            Modifier.fillMaxHeight(0.8f)
        }
            .fillMaxWidth(
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    0.1f
                }else{
                    0.2f
                })
            .constrainAs(c1){
                top.linkTo(card.bottom)
                start.linkTo(parent.start)
                end.linkTo(c2.start)
            } ) {
            itemsIndexed(history.getGameCountHistory()){ index, item ->
                Text(text = if(history.getGameCountHistory().isNotEmpty())
                    "$item"
                else
                    "",
                    modifier = Modifier,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium)
            }
        }
        LazyColumn(modifier= if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Modifier.fillMaxHeight(0.65f)
        }else{
            Modifier.fillMaxHeight(0.8f)
        }
            .fillMaxWidth(
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    0.9f
                }else{
                    0.8f
                })
            .constrainAs(c2){
                top.linkTo(card.bottom)
                start.linkTo(c1.end)
                end.linkTo(parent.end)
            }) {
            itemsIndexed(history.getGameSequenceHistory()){ index, item ->
                Text(text = if(history.getGameSequenceHistory().isNotEmpty()){
                    if(item.length>34 && orientation == Configuration.ORIENTATION_PORTRAIT){
                        item.take(34)+"..."
                    }else if(item.length>88 && orientation == Configuration.ORIENTATION_LANDSCAPE){
                        item.take(88)+"..."
                    }else{
                        item
                    }
                }
                    else
                        "",
                    modifier = Modifier,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium)
            }
        }

    }
}
