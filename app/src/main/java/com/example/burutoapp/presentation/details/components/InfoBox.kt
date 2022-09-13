package com.example.burutoapp.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.burutoapp.R
import com.example.burutoapp.presentation.theme.INFO_ICON_SIZE
import com.example.burutoapp.presentation.theme.SMALL_PADDING
import com.example.burutoapp.presentation.theme.titleColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    smallText: String,
    bigText: String,
    textColor: Color
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(
            modifier= Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            painter = icon,
            contentDescription = stringResource(R.string.info_box),
            tint = iconColor
        )
        Column{
            Text(
                text = bigText,
                color = textColor,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier= Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                style = MaterialTheme.typography.overline,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevInfoBox() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_bolt),
        iconColor = MaterialTheme.colors.primary,
        smallText ="power",
        bigText ="92",
        textColor = MaterialTheme.colors.titleColor
    )
}