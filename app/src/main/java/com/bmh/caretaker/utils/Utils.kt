package com.bmh.caretaker.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Utils {

    /**
     *  Convert Uri image to base64
     */
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

    /**
     * Decode string base64 to bytearray base64
     */
    fun imageFromBase64(base64String: String): ByteArray? {
        return Base64.decode(base64String, Base64.DEFAULT)
    }

    /**
     * Get current date time in this format:
     * dd/MM/yyyy HH:mm:ss
     */
    fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        return currentDateTime.format(formatter)
    }

    /**
     * Show toast message
     */
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Convert to 12h
     */
    fun convertTo12h(hour: Int = 0, minute: Int = 0): List<String> {
        val time24h = "$hour:$minute"

        val localTime = LocalTime.parse(time24h, DateTimeFormatter.ofPattern("H:m"))

        // Define a custom formatter with AM/PM
        val formatter = DateTimeFormatter.ofPattern("h:mm a")

        // Format the LocalTime object with the formatter
        val formattedTime = localTime.format(formatter)

        return formattedTime.split(" ")
    }
}