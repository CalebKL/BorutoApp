package com.example.burutoapp.presentation.homescreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.CANVAS_STAR_SIZE
import com.example.burutoapp.presentation.theme.StarColor
import com.example.burutoapp.util.Constants.LEFT_WIDTH


@Composable
fun RatingWidget(
    modifier: Modifier,
    rating:Double
) {
    val starStringPath = stringResource(id = R.string.star_path)
    val starPath = remember { PathParser().parsePathString(pathData = starStringPath).toPath() }

    val startPathBounce = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBounds =startPathBounce)
}

@Composable
fun FilledStar(
    starPath:Path,
    starPathBounds:Rect,
    scaleFactor: Float = 1f
) {
    Canvas(modifier =Modifier.size(CANVAS_STAR_SIZE)){
        val canvasSize = this.size
        val pathWidth = starPathBounds.width
        val pathHeight = starPathBounds.height
        val left = (canvasSize.width/LEFT_WIDTH)- (pathWidth/LEFT_WIDTH)
        val top = (canvasSize.height/LEFT_WIDTH)- (pathHeight/LEFT_WIDTH)

        translate(left= left, top = top ){
            drawPath(
                path= starPath,
                color = StarColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 2.0)
}




























































































