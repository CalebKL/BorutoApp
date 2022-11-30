package com.example.burutoapp.presentation.details.components

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.*
import com.example.burutoapp.util.Constants.ABOUT_MAX_LINES
import com.example.burutoapp.util.Constants.BASE_URL
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String, String>
){
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 =selectedHero){
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant))
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction ==1f)
            EXTRA_LARGE_PADDING
        else RADIUS_DP)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape= RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim,
        ),
        sheetPeekHeight = SHEET_PEEK_HEIGHT,
        sheetContent ={
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        } ,
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    backgroundColor= Color(parseColor(darkVibrant)),
                    imageFraction = currentSheetFraction,
                    onCloseClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor:Color = MaterialTheme.colors.primary,
    sheetBackgroundColor:Color= MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
){
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier= Modifier.size(INFO_ICON_SIZE),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.logo_icon),
                tint = contentColor
            )
            Text(
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor =infoBoxIconColor,
                smallText = stringResource(R.string.power),
                bigText = "${selectedHero.power}",
                textColor =contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calender),
                iconColor =infoBoxIconColor,
                smallText = stringResource(R.string.month),
                bigText = "${selectedHero.month}",
                textColor =contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor =infoBoxIconColor,
                smallText = stringResource(R.string.birthday),
                bigText = "${selectedHero.day}",
                textColor =contentColor
            )
        }
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_MAX_LINES
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family ,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities ,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes ,
                textColor = contentColor
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor:Color = MaterialTheme.colors.surface,
    onCloseClick:()->Unit
) {
    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl){
        error(R.drawable.ic_placeholder)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.4f)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClick() }
            ) {
                Icon(
                    modifier= Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_search_bar),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction:Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        Log.d("Fraction", fraction.toString())
        Log.d("Target", targetValue.toString())
        Log.d("Current", currentValue.toString())

        return when{
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed ->1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded ->0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded ->1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed ->1f + fraction
            else -> fraction
        }
}
@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetContent(selectedHero = Hero(
        id = 1,
        name = "Sasuke",
        image = "",
        about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sodales lorem enim, a volutpat nibh molestie in. Maecenas ante mauris, sodales eu nulla nec, faucibus auctor elit. Cras est dolor",
        rating = 5.5,
        power = 92,
        month = "July",
        day = "Monday",
        family = listOf("Sas", "Mon", "git"),
        abilities = listOf("Sas", "Mon", "git"),
        natureTypes =listOf("Sas", "Mon", "git")
    ))
}