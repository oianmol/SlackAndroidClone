package com.mutualmobile.praxis.utils

import java.io.BufferedReader

object FileUtil {

  fun loadText(resourceName: String): String = readResource(resourceName).use {
    it.readText()
  }

  private fun readResource(resourceName: String): BufferedReader {
    return javaClass.classLoader!!.getResource(resourceName)
        .openStream()
        .bufferedReader()
  }
}
