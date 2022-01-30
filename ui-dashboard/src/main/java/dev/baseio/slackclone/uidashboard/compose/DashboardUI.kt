package dev.baseio.slackclone.uidashboard.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.commonui.reusable.SlackDragComposableView
import dev.baseio.slackclone.uichat.models.ChatPresentation
import dev.baseio.slackclone.uidashboard.chat.ChatScreenUI
import dev.baseio.slackclone.uidashboard.home.*

@Composable
fun DashboardUI() {
  val scaffoldState = rememberScaffoldState()
  val dashboardNavController = rememberNavController()

  SlackCloneTheme {
    var lastChannel by remember {
      mutableStateOf<ChatPresentation.SlackChannel?>(null)
    }
    var isLeftNavOpen by remember { mutableStateOf(false) }
    var isChatViewClosed by remember { mutableStateOf(true) }
    val sideNavWidth = LocalConfiguration.current.screenWidthDp.dp * 0.8f
    val pxValue = with(LocalDensity.current) { sideNavWidth.toPx() }

    SlackDragComposableView(
      isLeftNavOpen = isLeftNavOpen,
      isChatViewClosed = lastChannel == null || isChatViewClosed,
      canOpenChatView = lastChannel != null,
      mainScreenOffset = (pxValue),
      onOpenCloseLeftView = {
        isLeftNavOpen = it
      },
      onOpenCloseRightView = {
        isChatViewClosed = it
      }, { modifier ->
        DashboardScaffold(isLeftNavOpen, scaffoldState, dashboardNavController, modifier, {
          isLeftNavOpen = !isLeftNavOpen
        }) {
          lastChannel = it
          isChatViewClosed = false
        }
      }, { leftViewModifier ->
        SideNavigation(leftViewModifier.width(sideNavWidth))
      }, { chatViewModifier ->
        lastChannel?.let { slackChannel ->
          ChatScreenUI(chatViewModifier, slackChannel, {
            isChatViewClosed = true
          })
        }
      }
    )

  }


}

@Composable
private fun DashboardScaffold(
  isLeftNavOpen: Boolean,
  scaffoldState: ScaffoldState,
  dashboardNavController: NavHostController,
  modifier: Modifier,
  appBarIconClick: () -> Unit,
  onItemClick: (ChatPresentation.SlackChannel) -> Unit,
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
            HomeScreenUI(appBarIconClick, onItemClick)
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
    if (isLeftNavOpen) {
      OverlayDark(appBarIconClick)
    }
  }
}

@Composable
private fun OverlayDark(appBarIconClick: () -> Unit) {
  Box(
    Modifier
      .fillMaxSize()
      .clickable {
        appBarIconClick()
      }
      .background(Color.Black.copy(alpha = 0.4f))
  ) {

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
  Column(Modifier.background(color = SlackCloneColorProvider.colors.uiBackground)) {
    Divider(
      color = SlackCloneColorProvider.colors.textPrimary.copy(alpha = 0.2f),
      thickness = 0.5.dp
    )
    BottomNavigation(backgroundColor = SlackCloneColorProvider.colors.uiBackground) {
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
    selectedContentColor = SlackCloneColorProvider.colors.bottomNavSelectedColor,
    unselectedContentColor = SlackCloneColorProvider.colors.bottomNavUnSelectedColor,
    icon = { Icon(screen.image, contentDescription = null) },
    label = {
      Text(
        stringResource(screen.resourceId),
        style = SlackCloneTypography.caption
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

