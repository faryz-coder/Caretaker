package com.bmh.caretaker.utils

import android.util.Base64
import java.io.File
import java.io.FileInputStream

class Utils {
    fun convertImageToBase64(imagePath: String): String? {
        return try {
            val file = File(imagePath)
            val inputStream = FileInputStream(file)
            val buffer = ByteArray(inputStream.available())
            inputStream.read()
            inputStream.close()

            Base64.encodeToString(buffer, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}