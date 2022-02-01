package dev.baseio.slackclone.commonui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = SlackCloneColorPalette(
  brand = SlackCloneColor,
  accent = SlackCloneColor,
  uiBackground = Color.White,
  textPrimary = Color.Black,
  textSecondary = Color.DarkGray,
  error = FunctionalRed,
  statusBarColor = SlackCloneColor,
  isDark = false,
  buttonColor = Color.Black,
  buttonTextColor = Color.White,
  darkBackground = DarkBackground,
  appBarColor = SlackCloneColor,
  lineColor = LineColorLight,
  bottomNavSelectedColor = Color.Black,
  bottomNavUnSelectedColor = Color.LightGray,
  appBarIconColor = Color.White,
  appBarTextTitleColor = Color.White,
  appBarTextSubTitleColor = Color.White,
  sendButtonDisabled = Color.LightGray,
  sendButtonEnabled = Color.Black
)

private val DarkColorPalette = SlackCloneColorPalette(
  brand = SlackCloneColor,
  accent = SlackCloneColor,
  uiBackground = DarkBackground,
  textPrimary = Color.White,
  textSecondary = Color.White,
  error = FunctionalRedDark,
  statusBarColor = SlackCloneColor,
  isDark = true,
  buttonColor = Color.White,
  buttonTextColor = Color.Black,
  darkBackground = DarkBackground,
  appBarColor = DarkAppBarColor,
  lineColor = LineColorDark,
  bottomNavSelectedColor = Color.White,
  bottomNavUnSelectedColor = Color.Gray,
  appBarIconColor = Color.White,
  appBarTextTitleColor = Color.White,
  appBarTextSubTitleColor = Color.White,
  sendButtonDisabled = Color.White.copy(alpha = 0.4f),
  sendButtonEnabled = Color.Blue
)

@Composable
fun SlackCloneTheme(
  isDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (isDarkTheme) DarkColorPalette else LightColorPalette
  val sysUiController = rememberSystemUiController()

  SideEffect {
    sysUiController.setSystemBarsColor(color = colors.appBarColor)
    sysUiController.setNavigationBarColor(color = colors.appBarColor)
  }

  ProvideSlackCloneColors(colors) {
    MaterialTheme(
      colors = debugColors(isDarkTheme),
      typography = SlackCloneTypography,
      shapes = SlackCloneShapes,
      content = content
    )
  }
}

object SlackCloneColorProvider {
  val colors: SlackCloneColorPalette
    @Composable
    get() = LocalSlackCloneColor.current
}

/**
 * SlackClone custom Color Palette
 */
@Stable
class SlackCloneColorPalette(
  brand: Color,
  accent: Color,
  uiBackground: Color,
  textPrimary: Color = brand,
  textSecondary: Color,
  error: Color,
  statusBarColor: Color,
  isDark: Boolean,
  buttonColor: Color,
  buttonTextColor: Color,
  darkBackground: Color,
  appBarColor: Color,
  lineColor: Color,
  bottomNavSelectedColor: Color,
  bottomNavUnSelectedColor: Color,
  appBarIconColor: Color,
  appBarTextTitleColor: Color,
  appBarTextSubTitleColor: Color,
  sendButtonDisabled:Color,
  sendButtonEnabled:Color
) {
  var brand by mutableStateOf(brand)
    private set
  var accent by mutableStateOf(accent)
    private set
  var uiBackground by mutableStateOf(uiBackground)
    private set
  var statusBarColor by mutableStateOf(statusBarColor)
    private set
  var textPrimary by mutableStateOf(textPrimary)
    private set
  var textSecondary by mutableStateOf(textSecondary)
    private set
  var error by mutableStateOf(error)
    private set
  var isDark by mutableStateOf(isDark)
    private set
  var buttonColor by mutableStateOf(buttonColor)
    private set
  var buttonTextColor by mutableStateOf(buttonTextColor)
    private set
  var darkBackground by mutableStateOf(darkBackground)
    private set
  var appBarColor by mutableStateOf(appBarColor)
    private set
  var lineColor by mutableStateOf(lineColor)
    private set

  var bottomNavSelectedColor by mutableStateOf(bottomNavSelectedColor)
    private set
  var bottomNavUnSelectedColor by mutableStateOf(bottomNavUnSelectedColor)
    private set
  var appBarIconColor by mutableStateOf(appBarIconColor)
    private set

  var appBarTextTitleColor by mutableStateOf(appBarTextTitleColor)
    private set
  var appBarTextSubTitleColor by mutableStateOf(appBarTextSubTitleColor)
    private set
  var sendButtonDisabled by mutableStateOf(sendButtonDisabled)
    private set

  var sendButtonEnabled by mutableStateOf(sendButtonEnabled)
    private set


  fun update(other: SlackCloneColorPalette) {
    brand = other.brand
    uiBackground = other.uiBackground
    textPrimary = other.textPrimary
    textSecondary = other.textSecondary
    error = other.error
    statusBarColor = other.statusBarColor
    isDark = other.isDark
    buttonColor = other.buttonColor
    buttonTextColor = other.buttonTextColor
    darkBackground = other.darkBackground
    appBarColor = other.appBarColor
    lineColor = other.lineColor
    bottomNavSelectedColor = other.bottomNavSelectedColor
    bottomNavUnSelectedColor = other.bottomNavUnSelectedColor
    appBarIconColor = other.appBarIconColor
    appBarTextTitleColor = other.appBarTextTitleColor
    appBarTextSubTitleColor = other.appBarTextSubTitleColor
    sendButtonEnabled = other.sendButtonEnabled
    sendButtonDisabled = other.sendButtonDisabled
  }
}

@Composable
fun ProvideSlackCloneColors(
  colors: SlackCloneColorPalette,
  content: @Composable () -> Unit
) {
  val colorPalette = remember { colors }
  colorPalette.update(colors)
  CompositionLocalProvider(LocalSlackCloneColor provides colorPalette, content = content)
}

private val LocalSlackCloneColor = staticCompositionLocalOf<SlackCloneColorPalette> {
  error("No SlackCloneColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [SlackCloneColorProvider.colors].
 */
fun debugColors(
  darkTheme: Boolean,
  debugColor: Color = Color.Red
) = Colors(
  primary = debugColor,
  primaryVariant = debugColor,
  secondary = debugColor,
  secondaryVariant = debugColor,
  background = debugColor,
  surface = debugColor,
  error = debugColor,
  onPrimary = debugColor,
  onSecondary = debugColor,
  onBackground = debugColor,
  onSurface = debugColor,
  onError = debugColor,
  isLight = !darkTheme
)