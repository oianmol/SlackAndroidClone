package com.mutualmobile.praxis.domain.usecases

/**
 * Created by Vipul Asri on 13/01/21.
 */

interface BaseUseCase<out Result, in ExecutableParam> {

  /**
   * Perform an operation with no input parameters.
   * Will throw an exception by default, if not implemented but invoked.
   *
   * @return
   */
  suspend fun perform(): Result = throw NotImplementedError()

  /**
   * Perform an operation.
   *  Will throw an exception by default, if not implemented but invoked.
   *
   * @param params
   * @return
   */
  suspend fun perform(params: ExecutableParam): Result = throw NotImplementedError()
}