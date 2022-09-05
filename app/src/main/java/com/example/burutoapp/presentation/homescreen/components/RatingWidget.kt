package com.example.burutoapp.presentation.homescreen.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.CANVAS_STAR_SIZE
import com.example.burutoapp.presentation.theme.EXTRA_SMALL_PADDING
import com.example.burutoapp.presentation.theme.StarColor


@Composable
fun RatingWidget(
    modifier: Modifier,
    rating:Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = EXTRA_SMALL_PADDING
) {
    val result = CalculateStars(rating = rating)
    val starStringPath = stringResource(id = R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starStringPath).toPath() }

    val startPathBounce = remember {
        starPath.getBounds()
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(EXTRA_SMALL_PADDING)
    ){
        result["filledStars"]?.let {
            repeat(it){
                FilledStar(
                    starPath = starPath,
                    starPathBounds = startPathBounce,
                    scaleFactor = 3f
                )
            }
        }
        result["halfFilledStar"]?.let {
            repeat(it){
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = startPathBounce,
                    scaleFactor = 3f
                )
            }
        }
        result["emptyStars"]?.let {
            repeat(it){
                EmptyStar(
                    starPath = starPath,
                    starPathBounds = startPathBounce,
                    scaleFactor = 3f
                )
            }
        }
    }
}

@Composable
fun FilledStar(
    starPath:Path,
    starPathBounds:Rect,
    scaleFactor: Float
) {
    Canvas(modifier =Modifier.size(CANVAS_STAR_SIZE)){
        val canvasSize = size
       scale(scale= scaleFactor){
           val pathWidth = starPathBounds.width
           val pathHeight = starPathBounds.height
           val left = (canvasSize.width/2f)- (pathWidth/1.7f)
           val top = (canvasSize.height/2f)- (pathHeight/1.7f)

           translate(left= left, top = top ){
               drawPath(
                   path= starPath,
                   color = StarColor
               )
           }
       }
    }
}

@Composable
fun HalfFilledStar(
    starPath:Path,
    starPathBounds:Rect,
    scaleFactor: Float
) {
    Canvas(modifier =Modifier.size(CANVAS_STAR_SIZE)){
        val canvasSize = size
        scale(scale= scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width/2f)- (pathWidth/1.7f)
            val top = (canvasSize.height/2f)- (pathHeight/1.7f)

            translate(left= left, top = top ){
                drawPath(
                    path= starPath,
                    color = Color.LightGray.copy(0.5f)
                )
                clipPath(path = starPath){
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.7f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath:Path,
    starPathBounds:Rect,
    scaleFactor: Float
) {
    Canvas(modifier =Modifier.size(CANVAS_STAR_SIZE)){
        val canvasSize = size
        scale(scale= scaleFactor){
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width/2f)- (pathWidth/1.7f)
            val top = (canvasSize.height/2f)- (pathHeight/1.7f)

            translate(left= left, top = top ){
                drawPath(
                    path= starPath,
                    color = Color.LightGray.copy(0.5f)
                )
            }
        }
    }
}

@Composable
fun CalculateStars(rating: Double): Map<String, Int>{
    val maxStars by remember { mutableStateOf(5) }
    var halfFilledStar by remember { mutableStateOf(0) }
    var filledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating){
        val( firstNumber, secondNumber) = rating.toString()
            .split("")
            .map {it.toInt()}

        if (firstNumber in 0..5 && secondNumber in 0..9){
            filledStars =firstNumber
            if (secondNumber in 1..5){
                halfFilledStar++
            }
            if (secondNumber in 6..9){
                filledStars ++
            }
            if(firstNumber == 5 && secondNumber >0){
                emptyStars = 5
                filledStars = 0
                halfFilledStar = 0
            }
        }else{
            Log.d("RatingWidget", "Invalid rating Number")
        }
    }
    emptyStars = maxStars - (filledStars + halfFilledStar)
    return  mapOf(
        "filledStars" to filledStars,
        "halfFilledStar" to halfFilledStar,
        "emptyStars" to emptyStars
    )
}

@Preview(showBackground = true)
@Composable
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 2.0)
}

@Preview(showBackground = true)
@Composable
fun HalfStarPrev() {
    val starStringPath = stringResource(id = R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starStringPath).toPath() }

    val startPathBounce = remember {
        starPath.getBounds()
    }
    HalfFilledStar(starPath  = starPath , starPathBounds = startPathBounce, scaleFactor = 3f)
}


@Preview(showBackground = true)
@Composable
fun EmptyStarPrev() {
    val starStringPath = stringResource(id = R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starStringPath).toPath() }

    val startPathBounce = remember {
        starPath.getBounds()
    }
    EmptyStar(starPath  = starPath , starPathBounds = startPathBounce, scaleFactor = 3f)
}



























































































