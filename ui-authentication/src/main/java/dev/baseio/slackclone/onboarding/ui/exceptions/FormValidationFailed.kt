package dev.baseio.slackclone.onboarding.ui.exceptions

import dev.baseio.slackclone.onboarding.ui.model.FailureType

class FormValidationFailed(val failType: FailureType) : Throwable()
