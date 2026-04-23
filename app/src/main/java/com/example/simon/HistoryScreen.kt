package com.example.simon

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun HistoryScreen(history: GameHistory){
    val orientation = LocalConfiguration.current.orientation

    ConstraintLayout(modifier = Modifier) {
        val (c1, c2) = createRefs()
        LazyColumn(modifier= Modifier
            .fillMaxHeight()
            .fillMaxWidth(
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    0.1f
                }else{
                    0.2f
                })
            .constrainAs(c1){
                start.linkTo(parent.start)
                end.linkTo(c2.start)
            } ) {
            itemsIndexed(history.getGameCountHistory()){ index, item ->
                Text(text = if(history.getGameCountHistory().isNotEmpty())
                    "$item"
                else
                    "")
            }
        }
        LazyColumn(modifier= Modifier
            .fillMaxHeight()
            .fillMaxWidth(
                if(orientation == Configuration.ORIENTATION_LANDSCAPE){
                    0.9f
                }else{
                    0.8f
                })
            .constrainAs(c2){
                start.linkTo(c1.end)
                end.linkTo(parent.end)
            }) {
            itemsIndexed(history.getGameSequenceHistory()){ index, item ->
                Text(text = if(history.getGameSequenceHistory().isNotEmpty())
                        item
                    else
                        "")
            }
        }

    }
}
