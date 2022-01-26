package dev.baseio.slackclone.auth.ui.exceptions

import dev.baseio.slackclone.auth.ui.model.FailureType

class FormValidationFailed(val failType: FailureType) : Throwable()
