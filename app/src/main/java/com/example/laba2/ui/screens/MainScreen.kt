package com.example.laba2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laba2.R
import com.example.laba2.ui.theme.YpBlue
import com.example.laba2.ui.theme.YpGray

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onPlaylistsClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Blue Header "Playlist Maker"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(YpBlue)
                .statusBarsPadding()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Menu items
        MainMenuItem(
            icon = painterResource(R.drawable.ic_search),
            title = stringResource(R.string.menu_search),
            onClick = onSearchClick
        )

        MainMenuItem(
            icon = painterResource(R.drawable.ic_playlists),
            title = stringResource(R.string.menu_playlists),
            onClick = onPlaylistsClick
        )

        MainMenuItem(
            icon = painterResource(R.drawable.ic_favorites),
            title = stringResource(R.string.menu_favorites),
            onClick = onFavoritesClick
        )

        MainMenuItem(
            icon = painterResource(R.drawable.ic_settings),
            title = stringResource(R.string.menu_settings),
            onClick = onSettingsClick
        )
    }
}

@Composable
private fun MainMenuItem(
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.ic_arrow_forward),
            contentDescription = null,
            tint = YpGray,
            modifier = Modifier.size(24.dp)
        )
    }
}
