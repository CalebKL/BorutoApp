package com.example.burutoapp.presentation.homescreen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.burutoapp.presentation.theme.*

@Composable
fun ShimmerEffect() {

}

@Composable
fun ShimmerItem() {
   Surface(
       modifier = Modifier
           .fillMaxWidth()
           .height(HERO_ITEM_HEIGHT),
       color = if (isSystemInDarkTheme())
           Color.Black else shimmerLightGray,
       shape = RoundedCornerShape(LARGE_PADDING)
   ) {
       Column(
           modifier = Modifier
               .padding(all = MEDIUM_PADDING),
           verticalArrangement = Arrangement.Bottom
       ){
           Surface(
               modifier = Modifier
                   .fillMaxWidth(0.5f)
                   .height(SHIMMER_HEIGHT),
               color = if (isSystemInDarkTheme())
                   shimmerDarkGray else shimmerMediumGray,
               shape = RoundedCornerShape(size = SMALL_PADDING)
           ){}
           Spacer(modifier = Modifier.padding(SMALL_PADDING))
           repeat(3){
               Surface(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(ABOUT_PLACE_HOLDER),
                   color = if (isSystemInDarkTheme())
                       shimmerDarkGray else shimmerMediumGray,
                   shape = RoundedCornerShape(size = SMALL_PADDING)
               ){}
               Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
           }
           Row(modifier = Modifier.fillMaxWidth()){
               repeat(5){
                   Surface(
                       modifier = Modifier
                           .size(PLACE_HOLDER_STAR_SHIMMER_EFFECT),
                       color = if (isSystemInDarkTheme())
                           shimmerDarkGray else shimmerMediumGray,
                       shape = RoundedCornerShape(size = SMALL_PADDING)
                   ){}
                   Spacer(modifier = Modifier.padding(SMALL_PADDING))
               }
           }
       }
    }
}


@Preview
@Composable
fun ShimmerItemPreview() {
    ShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItemPreview2() {
    ShimmerItem()
}





















