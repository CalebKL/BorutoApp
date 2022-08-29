package com.example.burutoapp.domain.models

import androidx.annotation.DrawableRes
import com.example.burutoapp.R

sealed class OnBoarding(
    @DrawableRes
    val image:Int,
    val title: String,
    val description: String
){
    object First: OnBoarding(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a Boruto Fan? Because if you are then we have great news for you!"
    )
    object Second: OnBoarding(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favourite heroes and learn some of the things you didn't know about"
    )
    object Third: OnBoarding(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your Heroes Power and see how much strong they are compared to others"
    )
}
