package com.example.burutoapp.presentation.homescreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.CANVAS_STAR_SIZE
import com.example.burutoapp.presentation.theme.StarColor


@Composable
fun RatingWidget(
    modifier: Modifier,
    rating:Double,
    scaleFactor: Float = 3f
) {
    val starStringPath = stringResource(id = R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starStringPath).toPath() }

    val startPathBounce = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBounds =startPathBounce, scaleFactor = 3f)
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



























































































