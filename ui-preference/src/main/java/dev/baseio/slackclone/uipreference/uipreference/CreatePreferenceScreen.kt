package dev.baseio.slackclone.uipreference.uipreference

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.uipreference.datastoremanager.DataStoreManager
import dev.baseio.slackclone.uipreference.model.Preference
import dev.baseio.slackclone.uipreference.model.PreferenceIcon
import dev.baseio.slackclone.uipreference.screen.PreferenceScreen
import dev.baseio.slackclone.uipreference.settings.*
import java.sql.Time

@Composable
fun CreatePreferenceScreen(composeNavigator: ComposeNavigator) {
        val scaffoldState = rememberScaffoldState()

        SlackCloneTheme {
            CreatePreference(scaffoldState, composeNavigator)
        }

}

@Composable
fun CreatePreference(scaffoldState: ScaffoldState,
                     composeNavigator: ComposeNavigator){
    val context = LocalContext.current
    val dataStore = context.dataStore
    val dataStoreManager = remember { DataStoreManager(dataStore) }

    Box {
        Scaffold(
            backgroundColor = SlackCloneColorProvider.colors.uiBackground,
            contentColor = SlackCloneColorProvider.colors.textSecondary,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            scaffoldState = scaffoldState,
            topBar = {
                CreatePreferenceAppBar(composeNavigator)
            },
        ) { innerPadding ->
            PreferenceContent(innerPadding,dataStoreManager)
        }
    }
}



@Composable
private fun CreatePreferenceAppBar(
    composeNavigator: ComposeNavigator
) {
    val haptic = LocalHapticFeedback.current
    SlackSurfaceAppBar(
        title = {
            NavTitle()
        },
        navigationIcon = {
            NavBackIcon(composeNavigator)
        },
        backgroundColor = SlackCloneColorProvider.colors.appBarColor,

    )
}

@Composable
private fun NavTitle() {
    Text(
        text = "Preferences",
        style = SlackCloneTypography.subtitle1.copy(color = SlackCloneColorProvider.colors.appBarTextTitleColor)
    )
}


@Composable
private fun NavBackIcon(composeNavigator: ComposeNavigator) {
    IconButton(onClick = {
        composeNavigator.navigateUp()
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Clear",
            modifier = Modifier.padding(start = 8.dp),
            tint = SlackCloneColorProvider.colors.appBarIconColor
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun PreferenceContent(innerPadding: PaddingValues,
                      dataStoreManager: DataStoreManager) {
    Box(modifier = Modifier.padding(innerPadding)) {
        SlackCloneSurface(
            modifier = Modifier.fillMaxSize()
        ) {
            PreferenceScreen(
                items = listOf(
                    CreateTimeAndPlacePreference(dataStoreManager = dataStoreManager),
                    CreateLookAndFeelPreference(dataStoreManager = dataStoreManager),
                    CreateAccessibilityPreference(dataStoreManager = dataStoreManager) ,
                    CreateAdvancedPreference(dataStoreManager = dataStoreManager),
                    CreateTroubleShootingPreference(dataStoreManager = dataStoreManager),
                    CreateAboutSlackPreference(dataStoreManager = dataStoreManager)

                ),
                dataStore = LocalContext.current.dataStore,
                contentPadding = innerPadding
            )
        }
    }
}








