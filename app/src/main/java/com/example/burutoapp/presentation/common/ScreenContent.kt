package com.example.burutoapp.presentation.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.burutoapp.R
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.navigation.Screen
import com.example.burutoapp.presentation.homescreen.components.RatingWidget
import com.example.burutoapp.presentation.homescreen.components.ShimmerEffect
import com.example.burutoapp.presentation.theme.*
import com.example.burutoapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ScreenContent(
    navController: NavHostController,
    heroes: LazyPagingItems<Hero>
) {
    val result = handlePagingRequest(heroes = heroes)
    if (result){
        Log.d("ScreenContent", heroes.loadState.toString())
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ){
            items(
                items = heroes,
                key = { hero->
                    hero.id
                }
            ){ hero->
                hero?.let {
                    HeroItem(navController = navController, hero = it)
                }
            }
        }
    }
}


@Composable
fun handlePagingRequest(
    heroes: LazyPagingItems<Hero>
): Boolean {
   heroes.apply {
       val error = when{
           loadState.append is LoadState.Error -> loadState.append as LoadState.Error
           loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
           loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
           else -> null
       }
       return when{
           loadState.refresh is LoadState.Loading ->{
               ShimmerEffect()
               false
           }
           error != null ->{
               EmptyScreen(error = error, heroes = heroes)
               false
           }
           heroes.itemCount <1 ->{
               EmptyScreen()
               false
           }
           else-> {
               true
           }
       }
   }
}
@ExperimentalCoilApi
@Composable
fun HeroItem(
    navController: NavHostController,
    hero: Hero
) {
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}"){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Detail.passHeroId(heroId = hero.id))
            }
            .padding(8.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING,
            )
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(MEDIUM_PADDING))
            {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontStyle= MaterialTheme.typography.h5.fontStyle,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontStyle= MaterialTheme.typography.subtitle1.fontStyle,
                    fontWeight = FontWeight.Normal,
                    maxLines= 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "${hero.rating}",
                        textAlign= TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(navController = rememberNavController(), hero =Hero(
        id = 1,
        name ="Sasuke",
        about = "edwdwd",
        image="",
        rating = 4.5,
        power = 100,
        month = "",
        day = "",
        family = listOf(),
        abilities = listOf(),
        natureTypes = listOf()
    ))
}




























































































