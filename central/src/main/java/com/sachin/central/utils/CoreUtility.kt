package com.sachin.central.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.sachin.central.BuildConfig.IS_LOG_ON
import com.sachin.central.R

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
object CoreUtility {

  fun printLog(tag: String, message: String) {
    when (IS_LOG_ON) {
      true -> Log.e(tag, message)
    }
  }


  @JvmStatic
  fun isInternetConnected(context: Context): Boolean {

    var result = false
    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val networkCapabilities = connectivityManager.activeNetwork ?: return false
      val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
      result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
      }
    } else {
      connectivityManager.run {
        connectivityManager.activeNetworkInfo?.run {
          result = when (type) {
            ConnectivityManager.TYPE_WIFI -> true
            ConnectivityManager.TYPE_MOBILE -> true
            ConnectivityManager.TYPE_ETHERNET -> true
            else -> false
          }

        }
      }
    }

    return result
  }

  fun checkNetworkAndToast(context: Context): Boolean {
    return if (!isInternetConnected(context)) {
      Toast.makeText(
        context,
        context.getString(R.string.check_internet), Toast.LENGTH_SHORT
      ).show()

      false
    } else {
      true
    }

  }
}