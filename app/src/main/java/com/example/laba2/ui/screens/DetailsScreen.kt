package com.example.laba2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laba2.R
import com.example.laba2.data.Track
import com.example.laba2.ui.theme.YpGray

@Composable
fun DetailsScreen(
    track: Track,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Track cover",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp)),
            tint = Color.LightGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = track.trackName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = track.artistName,
            fontSize = 14.sp,
            color = YpGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        DetailRow(title = "Длительность", value = track.trackTime)
        if (track.collectionName.isNotEmpty()) {
            DetailRow(title = "Альбом", value = track.collectionName)
        }
        if (track.releaseDate.isNotEmpty()) {
            DetailRow(title = "Год", value = track.releaseDate)
        }
        if (track.primaryGenreName.isNotEmpty()) {
            DetailRow(title = "Жанр", value = track.primaryGenreName)
        }
        if (track.country.isNotEmpty()) {
            DetailRow(title = "Страна", value = track.country)
        }
    }
}

@Composable
private fun DetailRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 13.sp, color = YpGray)
        Text(text = value, fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground)
    }
}
