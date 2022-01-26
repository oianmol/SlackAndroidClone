package dev.baseio.slackclone.onboarding.vm

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.baseio.slackclone.navigator.ComposeNavigator
import dev.baseio.slackclone.navigator.FragmentNavGraphNavigator
import dev.baseio.slackclone.onboarding.ui.model.LoginForm
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class AuthVMTest {

  @MockK
  lateinit var navigator: ComposeNavigator
  @MockK
  lateinit var fragmentNavigator: FragmentNavGraphNavigator
  @MockK
  private lateinit var savedStateHandle: SavedStateHandle

  private lateinit var authVM: AuthVM

  @Before
  fun setUp() {
    MockKAnnotations.init(this,  true)
    Dispatchers.setMain(StandardTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `test that login now fails with validation exception`() {
    runTest {
      launch {
        coEvery {
          navigator.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
          savedStateHandle.get<String>(any())
        } returns ""

        authVM = AuthVM(savedStateHandle, fragmentNavigator, navigator)


        authVM.uiState.test {
          assert(awaitItem() is AuthVM.UiState.Empty)
        }
        authVM.loginNow()
        authVM.uiState.test {
          assert(awaitItem() is AuthVM.UiState.ErrorState)
        }
      }
    }
  }

  @Test
  fun `test that loginNow() completes with no exception`() {
    runTest {
      launch {
        coEvery {
          navigator.observeResult<String>(any())
        } returns emptyFlow()

        coEvery {
          fragmentNavigator.navigateFragment(any())
        } returns Unit

        coEvery {
          savedStateHandle.get<String>(any())
        } returns ""

        authVM = AuthVM(savedStateHandle, fragmentNavigator, navigator)
        authVM.credentials.value = LoginForm("anmol@gmail.com", "sdkfkjkjfdsjkfds")

        authVM.uiState.test {
          assert(awaitItem() is AuthVM.UiState.Empty)
        }

        authVM.loginNow()

        authVM.uiState.test {
          assert(awaitItem() is AuthVM.UiState.SuccessState)
        }
      }
    }
  }
}