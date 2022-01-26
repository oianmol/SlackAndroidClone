package dev.baseio.slackclone.onboarding.ui.model

import com.praxis.feat.onboarding.services.Validatable
import com.praxis.feat.onboarding.ui.exceptions.FormValidationFailed
import java.util.regex.Pattern


data class LoginForm(val email: String = "", var password: String = "") : Validatable {
  override fun validate() {
    val pattern = emailRegex()
    if (!pattern.matcher(email).matches()) {
      throw FormValidationFailed(FailureType.EMAIL_NOT_VALID)
    }

    if (password.length < 6) {
      throw FormValidationFailed(FailureType.PASSWORD_NOT_VALID)
    }
  }

  private fun emailRegex() = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
  )
}
