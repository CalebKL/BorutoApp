package com.example.burutoapp.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.burutoapp.presentation.theme.titleColor

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
){
    Column{
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed{ index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1} .$item",
                color = textColor,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderedListPrev() {
    OrderedList(
        title = "Family",
        items = listOf("Minator", "Kushina"),
        textColor = MaterialTheme.colors.titleColor
    )
}