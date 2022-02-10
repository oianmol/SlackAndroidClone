package dev.baseio.slackclone.uipreference.uipreference

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
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
                    CreateAdvancedPreference(dataStoreManager = dataStoreManager)

                ),
                dataStore = LocalContext.current.dataStore,
                contentPadding = innerPadding
            )
        }
    }
}

@Composable
fun CreateTimeAndPlacePreference(
                      dataStoreManager: DataStoreManager) : Preference.PreferenceGroup{

    val languagePreference by dataStoreManager.getPreferenceFlow(LanguagePreference)
        .collectAsState(initial = "")
    val timezonePreference by dataStoreManager.getPreferenceFlow(TimeZonePreference)
        .collectAsState(initial = true)
    return Preference.PreferenceGroup(
        title = "Time and place",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.ListPreference(
                request = LanguagePreference,
                title = "Language",
                summary = languagePreference,
                singleLineTitle = true,
                icon = { PreferenceIcon(icon = Icons.Outlined.CheckCircle) },
                entries = mapOf(
                    "key1" to "English (US)",
                    "key2" to "English (UK)"
                )
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = TimeZonePreference,
                title = "Set time zone automatically",
                summary = "(UTC+5:30) Chennai, Kolkata, Mumbai, New Delhi",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
        )
    )

}

@Composable
fun CreateLookAndFeelPreference(
    dataStoreManager: DataStoreManager) : Preference.PreferenceGroup{

    val darkModePreference by dataStoreManager.getPreferenceFlow(DarkModePreference)
        .collectAsState(initial = "")
    return Preference.PreferenceGroup(
        title = "Dark Mode",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.ListPreference(
                request = DarkModePreference,
                title = "Dark Mode",
                summary = darkModePreference,
                singleLineTitle = true,
                icon = { PreferenceIcon(icon = Icons.Outlined.CheckCircle) },
                entries = mapOf(
                    "key1" to "System default",
                    "key2" to "Off",
                    "key2" to "On"
                )
            ),
        )
    )

}

@Composable
fun CreateAdvancedPreference(
    dataStoreManager: DataStoreManager) : Preference.PreferenceGroup{

    val openWebpagesInAppPreference by dataStoreManager.getPreferenceFlow(OpenWebpagesInAppPreference)
        .collectAsState(initial = true)
    val optimiseImageUploadsPreference by dataStoreManager.getPreferenceFlow(OptimiseImageUploadsPreference)
        .collectAsState(initial = true)
    val optimiseVideoUploadsPreference by dataStoreManager.getPreferenceFlow(OptimiseVideoUploadsPreference)
        .collectAsState(initial = true)
    val showImagePreviewsPreference by dataStoreManager.getPreferenceFlow(ShowImagePreviewsPreference)
        .collectAsState(initial = true)
    return Preference.PreferenceGroup(
        title = "Advanced",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.SwitchPreference(
                request = OpenWebpagesInAppPreference,
                title = "Open web pages in app",
                summary = "Allow links to open in slack",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = OptimiseImageUploadsPreference,
                title = "Optimise Image Uploads",
                summary = "Images are optimised for upload performance",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = OptimiseVideoUploadsPreference,
                title = "Optimise Video Uploads",
                summary = "Videos are optimised for upload performance",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = ShowImagePreviewsPreference,
                title = "Show image previews",
                summary = "Show previews of images and files",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
        )
    )

}

@Composable
fun CreateAccessibilityPreference(
    dataStoreManager: DataStoreManager) : Preference.PreferenceGroup {

    val allowAnimatedImagesAndEmojiPreference by dataStoreManager.getPreferenceFlow(
        AllowAnimatedImagesAndEmojiPreference
    )
        .collectAsState(initial = true)
    val underlineLinksPreference by dataStoreManager.getPreferenceFlow(UnderlineLinksPreference)
        .collectAsState(initial = true)
    val displayTypingIndicatorsPreference by dataStoreManager.getPreferenceFlow(
        DisplayTypingIndicatorsPreference
    )
        .collectAsState(initial = true)
    return Preference.PreferenceGroup(
        title = "Accessibility",
        enabled = true,
        preferenceItems = listOf(
            Preference.PreferenceItem.SwitchPreference(
                request = AllowAnimatedImagesAndEmojiPreference,
                title = "Allow animated images $ emoji",
                summary = "This only affects what you see",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = UnderlineLinksPreference,
                title = "Underline links",
                summary = "Websites and hyperlinks will be underlined in conversations",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
            Preference.PreferenceItem.SwitchPreference(
                request = DisplayTypingIndicatorsPreference,
                title = "Display Typing Indicators",
                summary = "See when someone is typing",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                true
            ),
        )
    )
}

