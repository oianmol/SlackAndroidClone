package com.mutualmobile.feat.githubrepos.utils

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.mutualmobile.feat.githubrepos.ui.github.repodetails.DATE_TIME_DETAILS
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.DurationUnit

fun getFormattedDuration(createDate: String): String {
  return (Clock.System.now() - createDate.toInstant()).toInt(DurationUnit.SECONDS)
    .let {
      return@let listOf(
        ":%02d".format(it % 60),
        ":%02d".format((it % 3600) / 60),
        "%d".format((it % 86400) / 3600),
        "%d days ".format((it % 31536000) / 86400),
        "%d years ".format(it / 31536000)
      )
        .filter { t -> !t.contains("0 days") && !t.contains("0 years") }
        .reversed()
        .joinToString("") { t ->
          when (t) {
            "1 days" -> "1 day"
            "1 years" -> "1 year"
            else -> t
          }
        }
    }
}

@RequiresApi(VERSION_CODES.O)
fun getFormattedCreateDate(createDate: String): String {
  return createDate.toInstant()
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .toJavaLocalDateTime()
    .format(DateTimeFormatter.ofPattern(DATE_TIME_DETAILS))
}