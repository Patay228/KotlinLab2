package com.example.laba2.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laba2.R
import com.example.laba2.ui.theme.YpBlue
import com.example.laba2.ui.theme.YpGray

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isDarkTheme by remember { mutableStateOf(false) }

    val shareLink = stringResource(R.string.share_link)
    val shareChooserTitle = stringResource(R.string.share_chooser_title)
    val supportEmail = stringResource(R.string.support_email)
    val supportSubject = stringResource(R.string.support_subject)
    val supportBody = stringResource(R.string.support_body)
    val userAgreementUrl = stringResource(R.string.user_agreement_url)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        // Top Bar: Back button + Screen title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
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
                text = stringResource(R.string.settings_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 1: Dark theme switch
        SettingsSwitchRow(
            title = stringResource(R.string.settings_dark_theme),
            checked = isDarkTheme,
            onCheckedChange = { isDarkTheme = it }
        )

        // Row 2: Share app
        SettingsActionRow(
            title = stringResource(R.string.settings_share),
            icon = painterResource(R.drawable.ic_share),
            onClick = {
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareLink)
                }
                val chooser = Intent.createChooser(sendIntent, shareChooserTitle)
                try {
                    context.startActivity(chooser)
                } catch (e: Exception) {
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        )

        // Row 3: Write to developers
        SettingsActionRow(
            title = stringResource(R.string.settings_support),
            icon = painterResource(R.drawable.ic_support),
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
                    putExtra(Intent.EXTRA_SUBJECT, supportSubject)
                    putExtra(Intent.EXTRA_TEXT, supportBody)
                }
                try {
                    context.startActivity(emailIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        )

        // Row 4: User agreement
        SettingsActionRow(
            title = stringResource(R.string.settings_agreement),
            icon = painterResource(R.drawable.ic_arrow_forward),
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(userAgreementUrl))
                try {
                    context.startActivity(browserIntent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun SettingsSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = YpBlue,
                checkedTrackColor = YpBlue.copy(alpha = 0.5f),
                uncheckedThumbColor = YpGray,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}

@Composable
private fun SettingsActionRow(
    title: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = icon,
            contentDescription = null,
            tint = YpGray,
            modifier = Modifier.size(24.dp)
        )
    }
}
