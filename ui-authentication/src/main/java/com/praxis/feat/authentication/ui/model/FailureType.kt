package com.praxis.feat.authentication.ui.model

enum class FailureType(val message: String) {
  EMAIL_NOT_VALID(message = "Email is not valid"),
  PASSWORD_NOT_VALID(message = "Password should be at least 6 characters long")
}
