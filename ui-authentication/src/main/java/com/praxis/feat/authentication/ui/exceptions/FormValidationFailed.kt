package com.praxis.feat.authentication.ui.exceptions

import com.praxis.feat.authentication.ui.model.FailureType

class FormValidationFailed(val failType: FailureType) : Throwable()
