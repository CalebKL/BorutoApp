package com.example.burutoapp.presentation.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.burutoapp.R
import com.example.burutoapp.domain.models.OnBoarding
import com.example.burutoapp.navigation.Screen
import com.example.burutoapp.presentation.theme.*
import com.example.burutoapp.util.Constants.CURRENT_PAGE
import com.example.burutoapp.util.Constants.ON_BOARDING_PAGES
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
) {
    val pages = listOf(
        OnBoarding.First,
        OnBoarding.Second,
        OnBoarding.Third
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackground)
    ){
        HorizontalPager(
            modifier= Modifier.weight(10f),
            state= pagerState,
            count = ON_BOARDING_PAGES,
            verticalAlignment = Alignment.Top
        ) {position->
            PagerScreen(onBoarding = pages[position])
        }
        HorizontalPagerIndicator(
            modifier= Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )
        FinishButton(
            pagerState = pagerState,
            modifier = Modifier.weight(2f)
        ) {
            navController.popBackStack()
            navController.navigate(route = Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
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

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onFinishClick:() ->Unit
) {
    Row(
        modifier = modifier.padding(EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
        AnimatedVisibility(
            modifier= Modifier.fillMaxWidth(),
            visible =pagerState.currentPage == CURRENT_PAGE,

        )
        {
            Button(
                onClick = onFinishClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            )
            {
                Text(text = stringResource(R.string.finish_button))
            }
        }
    }
}