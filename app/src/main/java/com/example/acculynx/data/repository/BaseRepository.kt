package com.example.acculynx.data.repository

import android.util.Log
import com.example.acculynx.data.network.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = questionsApiOutput(call, error)
        var data : T? = null

        when(result){
            is Output.Success ->
                data = result.data
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return data

    }
    private suspend fun<T : Any> questionsApiOutput(call: suspend()-> Response<T> , error: String) : Output<T> {
        val response = call.invoke()

        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }

}
