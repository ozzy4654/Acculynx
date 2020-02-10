package com.example.acculynx.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.acculynx.R

val networkRequest = NetworkRequest.Builder().build()
var dialog: AlertDialog? = null

object PreferenceHelper {
    fun customPrefs(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}

fun Networkee(activity: AppCompatActivity): ConnectivityManager.NetworkCallback {
    //network checker from Android Developers site
    //    val cm = activity.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    //    cm.registerNetworkCallback(networkRequest, networkCallback)
    return object : ConnectivityManager.NetworkCallback() {
        val builder = AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert)
            .setTitle("No Internet Connection")
            .setMessage("Loss of a Data connection. Please try connecting to WIFI or check your service provider")

        override fun onAvailable(network: Network) {
            dialog?.dismiss()
        }

        override fun onUnavailable() {
            super.onUnavailable()
            dialog = builder.show()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            dialog = builder.show()
        }
    }
}