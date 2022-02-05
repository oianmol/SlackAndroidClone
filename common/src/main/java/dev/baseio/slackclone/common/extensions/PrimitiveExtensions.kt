package dev.baseio.slackclone.common.extensions

import java.math.RoundingMode


fun String?.isValid(): Boolean {
    return !this.isNullOrEmpty()
}

fun Int.divide(divideBy: Double, decimals: Int = 2): String {
    return (this / divideBy).toBigDecimal().setScale(decimals, RoundingMode.UP).toPlainString()
}