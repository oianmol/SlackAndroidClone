package dev.baseio.slackclone.uidashboard.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.*
import dev.baseio.slackclone.uidashboard.custom.DragComposableView
import dev.baseio.slackclone.uidashboard.home.UserProfileUI

@Composable
fun DashboardUI() {
  val scaffoldState = rememberScaffoldState()
  val dashboardNavController = rememberNavController()

  val sysUiController = rememberSystemUiController()

  LaunchedEffect(Unit) {
    sysUiController.setSystemBarsColor(color = DarkBackground)
    sysUiController.setNavigationBarColor(color = Color.White)
  }

  var isOpenState by remember { mutableStateOf(false) }
  val pxValue = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

  Box(Modifier.fillMaxWidth()) {
    SideNavigation()
    DragComposableView(
      isOpen = isOpenState,
      dragOffset = (pxValue * 0.85f),
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

@Composable
private fun DashboardScaffold(
  scaffoldState: ScaffoldState,
  dashboardNavController: NavHostController,
  modifier: Modifier,
  appBarIconClick: () -> Unit
) {
  Scaffold(
    backgroundColor = SlackCloneTheme.colors.uiBackground,
    contentColor = SlackCloneTheme.colors.textSecondary,
    modifier = modifier
      .statusBarsPadding()
      .navigationBarsPadding(),

    scaffoldState = scaffoldState,
    topBar = {
      DashboardTopBar(appBarIconClick)
    },
    bottomBar = {
      DashboardBottomNavBar(dashboardNavController)
    },
    snackbarHost = {
      scaffoldState.snackbarHostState
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {

      }, backgroundColor = SlackCloneColor) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = Color.White)
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
          Modifier.padding(innerPadding)
        ) {
          composable(Screen.Home.route) {
            Text(text = "Home")
          }
          composable(Screen.DMs.route) {
            Text(text = "DMs")
          }
          composable(Screen.Mentions.route) {
            Text(text = "Mentions")
          }
          composable(Screen.Search.route) {
            Text(text = "Search")
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
  BottomNavigation(backgroundColor = Color.White) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val dashTabs = mutableListOf<Screen>().apply {
      add(Screen.Home)
      add(Screen.DMs)
      add(Screen.Mentions)
      add(Screen.Search)
      add(Screen.You)
    }
    dashTabs.forEach { screen ->
      BottomNavigationItem(
        selectedContentColor = DarkBackground,
        unselectedContentColor = Color.Black.copy(alpha = 0.6f),
        icon = { Icon(screen.image, contentDescription = null) },
        label = {
          Text(
            stringResource(screen.resourceId),
            style = SlackCloneTypography.subtitle2
          )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
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
      )
    }
  }
}

@Composable
private fun DashboardTopBar(appBarIconClick: () -> Unit) {
  SlackSurfaceAppBar(
    title = {
      Text(text = "mutualmobile", style = SlackCloneTypography.h5.copy(color = Color.White))
    },
    navigationIcon = {
      MMImageButton(appBarIconClick)
    },
    backgroundColor = DarkBackground,
  )
}

@Composable
private fun MMImageButton(appBarIconClick: () -> Unit) {
  IconButton(onClick = {
    appBarIconClick()
  }) {
    SlackImageBox(
      Modifier.size(38.dp),
      "https://avatars.slack-edge.com/2018-07-20/401750958992_1b07bb3c946bc863bfc6_88.png"
    )
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