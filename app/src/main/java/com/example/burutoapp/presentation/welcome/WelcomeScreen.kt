package com.example.burutoapp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.burutoapp.R
import com.example.burutoapp.domain.models.OnBoarding
import com.example.burutoapp.presentation.theme.*
import com.example.burutoapp.util.Constants.ON_BOARDING_PAGES
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val pages = listOf(
        OnBoarding.First,
        OnBoarding.Second,
        OnBoarding.Third
    )
    val pager = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackground)
    ){
        HorizontalPager(
            state= pager,
            count = ON_BOARDING_PAGES,
            verticalAlignment = Alignment.Top
        ) {position->
            PagerScreen(onBoarding = pages[position])
        }
    }
}

@Composable
fun PagerScreen(onBoarding: OnBoarding) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier= Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoarding.image),
            contentDescription = stringResource(
                R.string.welcome_image)
        )
        Text(
            modifier= Modifier
                .fillMaxWidth(),
            text = onBoarding.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoarding.description,
            color = MaterialTheme.colors.textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}