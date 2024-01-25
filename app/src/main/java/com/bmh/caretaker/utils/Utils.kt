package com.bmh.caretaker.utils

import android.content.Context
import android.net.Uri
import android.util.Base64

class Utils {
    fun convertImageToBase64(context: Context, imagePath: Uri): String? {
        return try {
           context.contentResolver.openInputStream(imagePath)?.readBytes().let { bytes ->
               Base64.encodeToString(bytes, Base64.DEFAULT)
           }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun imageFromBase64(base64String: String): ByteArray? {
        return Base64.decode(base64String, Base64.DEFAULT)
    }
}