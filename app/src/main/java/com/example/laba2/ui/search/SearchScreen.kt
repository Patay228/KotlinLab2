package com.example.laba2.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laba2.R
import com.example.laba2.domain.model.Track
import com.example.laba2.presentation.SearchState
import com.example.laba2.presentation.SearchViewModel
import com.example.laba2.ui.theme.YpBlue
import com.example.laba2.ui.theme.YpGray

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.getViewModelFactory()),
    onBackClick: (() -> Unit)? = null,
    navigateToDetailScreen: (Track) -> Unit = {}
) {
    val screenState by viewModel.searchScreenState.collectAsState()
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(if (onBackClick != null) Modifier.statusBarsPadding() else Modifier)
            .padding(horizontal = 16.dp)
    ) {
        if (onBackClick != null) {
            // Top App Bar with back button and screen title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.search_title),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        } else {
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = YpBlue,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = YpBlue
            ),
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_tracks_placeholder),
                    fontSize = 16.sp,
                    color = YpGray
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .clickable {
                            if (text.isNotBlank()) {
                                viewModel.searchTracks(text.trim())
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        }
                        .padding(8.dp),
                    imageVector = Icons.Filled.Search,
                    tint = YpGray,
                    contentDescription = stringResource(R.string.search_icon)
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                text = ""
                                viewModel.clearSearch()
                                keyboardController?.hide()
                            }
                            .padding(8.dp),
                        imageVector = Icons.Filled.Clear,
                        tint = YpGray,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotBlank()) {
                        viewModel.searchTracks(text.trim())
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }
            )
        )

        SearchResults(
            screenState = screenState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            onTrackClick = navigateToDetailScreen
        )
    }
}

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetailScreen: (Track) -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    SearchScreen(
        modifier = Modifier,
        viewModel = searchViewModel,
        onBackClick = navigateBack,
        navigateToDetailScreen = navigateToDetailScreen
    )
}

@Composable
private fun SearchResults(
    screenState: SearchState,
    modifier: Modifier = Modifier,
    onTrackClick: (Track) -> Unit
) {
    when (screenState) {
        is SearchState.Initial -> {
            // Начальное состояние: ничего не показываем
        }

        is SearchState.Searching -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = YpBlue
                )
            }
        }

        is SearchState.Success -> {
            val tracks = screenState.foundList
            if (tracks.isEmpty()) {
                InformerState(stringResource(R.string.search_nothing_found))
            } else {
                LazyColumn(modifier = modifier) {
                    itemsIndexed(tracks) { index, track ->
                        TrackListItem(
                            track = track,
                            onClick = { onTrackClick(track) }
                        )
                        if (index < tracks.size - 1) {
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(start = 56.dp)
                            )
                        }
                    }
                }
            }
        }

        is SearchState.Fail -> {
            InformerState(
                titleText = screenState.error,
                subtitleText = if (screenState.error == stringResource(R.string.search_nothing_found) ||
                    screenState.error == stringResource(R.string.no_tracks_found)
                ) null else stringResource(R.string.check_connection)
            )
        }
    }
}

@Composable
private fun InformerState(
    titleText: String,
    subtitleText: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        subtitleText?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                fontSize = 14.sp,
                color = YpGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun TrackListItem(
    track: Track,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rounded note placeholder
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Audiotrack,
                contentDescription = null,
                tint = YpGray,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = track.trackName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = track.artistName,
                fontSize = 12.sp,
                color = YpGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = track.trackTime,
            fontSize = 12.sp,
            color = YpGray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
