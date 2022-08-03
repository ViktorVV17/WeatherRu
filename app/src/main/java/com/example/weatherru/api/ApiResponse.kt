package com.example.weatherru.api

import com.google.gson.Gson
import retrofit2.Response

sealed class ApiResponse<T> {

    companion object {

        fun <T> error(error: Throwable): ApiCommonError<T> {
            val msg = error.message.orEmpty()
//            when (error) {
//                is .. todo additional error message edits if needed
//            }
            return ApiCommonError(msg)
        }

        fun <T> error(
            response: Response<T>,
            gson: Gson
        ): ApiResponse<T> {

            val regularError = parseRegularError(response, gson)
            val code = regularError?.code ?: "0"
            val msg = regularError?.message.orEmpty()//todo or default message from string resource
            return ApiRegularError(code, msg)
        }

        fun <T> success(response: Response<T>): ApiResponse<T> {
            val body = response.body()
            return if (body == null || response.code() == 204) {
                ApiEmptyResponse()
            } else {
                ApiSuccessResponse(body)
            }
        }

        private fun <T> parseRegularError(response: Response<T>, gson: Gson): RegularError? {
            return try {
                gson.fromJson(response.errorBody()?.charStream(), RegularError::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiCommonError<T>(val errorMessage: String) : ApiResponse<T>()

data class ApiRegularError<T>(val code: String, val msg: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()