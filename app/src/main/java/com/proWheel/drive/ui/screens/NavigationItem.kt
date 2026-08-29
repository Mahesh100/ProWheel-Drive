package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NavigationItem(

    text: String,

    selected: Boolean,

    onClick:
        () -> Unit
) {

    Surface(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 3.dp
                )
                .clickable {
                    onClick()
                },

        shape =
            MaterialTheme
                .shapes
                .medium,

        color =
            if (selected)

                MaterialTheme
                    .colorScheme
                    .primaryContainer

            else

                MaterialTheme
                    .colorScheme
                    .surface
    ) {

        Text(

            text =
                text,

            modifier =
                Modifier.padding(
                    horizontal = 18.dp,
                    vertical = 14.dp
                ),

            fontWeight =
                if (selected)

                    FontWeight.Bold

                else

                    FontWeight.Normal
        )
    }
}
