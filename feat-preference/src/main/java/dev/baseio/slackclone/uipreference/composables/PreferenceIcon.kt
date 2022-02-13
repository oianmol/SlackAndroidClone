package dev.baseio.slackclone.uipreference.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PreferenceIcon(icon: ImageVector, contentDescription: String? = null) {
    Icon(
        imageVector = icon,
        contentDescription,
        modifier = Modifier
            .padding(8.dp)
            .size(24.dp)
    )
}