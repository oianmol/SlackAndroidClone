package dev.baseio.slackclone.uidashboard.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.uidashboard.custom.DragComposableView
import dev.baseio.slackclone.uidashboard.home.*

@Composable
fun DashboardUI() {
  val scaffoldState = rememberScaffoldState()
  val dashboardNavController = rememberNavController()

  SlackCloneTheme {
    var isOpenState by remember { mutableStateOf(false) }
    val sideNavWidth = LocalConfiguration.current.screenWidthDp.dp * 0.8f
    val pxValue = with(LocalDensity.current) { sideNavWidth.toPx() }

    Box(Modifier.fillMaxWidth()) {
      SideNavigation(Modifier.width(sideNavWidth))
      DragComposableView(
        isOpen = isOpenState,
        dragOffset = (pxValue),
        onOpen = {
          isOpenState = true
        },
        onClose = {
          isOpenState = false
        }) { modifier ->
        DashboardScaffold(scaffoldState, dashboardNavController, modifier) {
          isOpenState = !isOpenState
        }
      }
    }
  }


}

@Composable
private fun DashboardScaffold(
  scaffoldState: ScaffoldState,
  dashboardNavController: NavHostController,
  modifier: Modifier,
  appBarIconClick: () -> Unit
) {
  Scaffold(
    backgroundColor = SlackCloneColorProvider.colors.uiBackground,
    contentColor = SlackCloneColorProvider.colors.textSecondary,
    modifier = modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    scaffoldState = scaffoldState,
    bottomBar = {
      DashboardBottomNavBar(dashboardNavController)
    },
    snackbarHost = {
      scaffoldState.snackbarHostState
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {

      }, backgroundColor = Color.White) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = SlackCloneColor)
      }
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      SlackCloneSurface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
      ) {
        NavHost(
          dashboardNavController,
          startDestination = Screen.Home.route,
        ) {
          composable(Screen.Home.route) {
            HomeScreenUI(appBarIconClick)
          }
          composable(Screen.DMs.route) {
            DirectMessagesUI()
          }
          composable(Screen.Mentions.route) {
            MentionsReactionsUI()
          }
          composable(Screen.Search.route) {
            SearchMessagesUI()
          }
          composable(Screen.You.route) {
            UserProfileUI()
          }
        }
      }
    }
  }
}

sealed class Screen(val route: String, val image: ImageVector, @StringRes val resourceId: Int) {
  object Home : Screen("Home", Icons.Filled.Home, dev.baseio.slackclone.uidashboard.R.string.home)
  object DMs : Screen("DMs", Icons.Filled.Menu, dev.baseio.slackclone.uidashboard.R.string.dms)
  object Mentions :
    Screen("Mentions", Icons.Filled.Email, dev.baseio.slackclone.uidashboard.R.string.mentions)

  object Search :
    Screen("Search", Icons.Filled.Search, dev.baseio.slackclone.uidashboard.R.string.search)

  object You : Screen("You", Icons.Default.Face, dev.baseio.slackclone.uidashboard.R.string.you)

}

@Composable
fun DashboardBottomNavBar(navController: NavHostController) {
  Column(Modifier.background(color = Color.Black)) {
    Divider(color = Color.White.copy(alpha = 0.2f), thickness = 0.5.dp)
    BottomNavigation(backgroundColor = DarkBackground) {
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination
      val dashTabs = getDashTabs()
      dashTabs.forEach { screen ->
        BottomNavItem(screen, currentDestination, navController)
      }
    }
  }
}

@Composable
private fun RowScope.BottomNavItem(
  screen: Screen,
  currentDestination: NavDestination?,
  navController: NavHostController
) {
  BottomNavigationItem(
    selectedContentColor = Color.White,
    unselectedContentColor = Color.DarkGray,
    icon = { Icon(screen.image, contentDescription = null) },
    label = {
      Text(
        stringResource(screen.resourceId),
        style = SlackCloneTypography.subtitle2
      )
    },
    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
    onClick = {
      navigateTab(navController, screen)
    }
  )
}

private fun navigateTab(
  navController: NavHostController,
  screen: Screen
) {
  navController.navigate(screen.route) {
    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    popUpTo(navController.graph.findStartDestination().id) {
      saveState = true
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
  }
}

private fun getDashTabs(): MutableList<Screen> {
  return mutableListOf<Screen>().apply {
    add(Screen.Home)
    add(Screen.DMs)
    add(Screen.Mentions)
    add(Screen.Search)
    add(Screen.You)
  }
}

@Composable
fun SlackImageBox(modifier: Modifier, imageUrl: String) {
  Image(
    painter = rememberImagePainter(
      data = imageUrl,
      builder = {
        transformations(RoundedCornersTransformation(12.0f, 12.0f, 12.0f, 12.0f))
      }
    ),
    contentDescription = null,
    modifier = modifier
  )
}