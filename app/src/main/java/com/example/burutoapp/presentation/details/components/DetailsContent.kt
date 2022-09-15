package com.example.burutoapp.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.*

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
){
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = SHEET_PEEK_HEIGHT,
        sheetContent ={

        } ,
        content = {}
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
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetContent(selectedHero = Hero(
        id = 1,
        name = "Sasuke",
        image = "",
        about = "ed3ed3d3",
        rating = 5.5,
        power = 92,
        month = "July",
        day = "Monday",
        family = listOf("Sas", "Mon", "git"),
        abilities = listOf("Sas", "Mon", "git"),
        natureTypes =listOf("Sas", "Mon", "git")
    ))
}