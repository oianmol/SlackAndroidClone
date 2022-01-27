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
  uiBackground = SlackCloneColor,
  textPrimary = Color.Black,
  textSecondary = TextSecondary,
  error = FunctionalRed,
  statusBarColor = SlackCloneColor,
  isDark = false,
  buttonColor = Color.White,
  buttonTextColor = SlackCloneColor,
  darkBackground = DarkBackground
)

private val DarkColorPalette = SlackCloneColorPalette(
  brand = SlackCloneColor,
  accent = SlackCloneColor,
  uiBackground = SlackCloneColor,
  textPrimary = Color.Black,
  textSecondary = Color.White,
  error = FunctionalRedDark,
  statusBarColor = SlackCloneColor,
  isDark = true,
  buttonColor = Color.White,
  buttonTextColor = SlackCloneColor,
  darkBackground = DarkBackground

)

@Composable
fun SlackCloneTheme(
  isDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (isDarkTheme) DarkColorPalette else LightColorPalette
  val sysUiController = rememberSystemUiController()

  SideEffect {
    sysUiController.setNavigationBarColor(color = colors.statusBarColor)
    sysUiController.setSystemBarsColor(color = colors.statusBarColor)
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

object SlackCloneTheme {
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
  darkBackground:Color
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
 * [MaterialTheme.colors] in preference to [SlackCloneTheme.colors].
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