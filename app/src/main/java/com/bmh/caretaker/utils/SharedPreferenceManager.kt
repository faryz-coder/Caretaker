package com.bmh.caretaker.utils

import android.content.SharedPreferences
import com.bmh.caretaker.model.Reminder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.UUID

class SharedPreferenceManager(val sharePref: SharedPreferences) {
    companion object {
        const val ITEM_LIST = "item_list"
    }

    fun addReminderInto(reminder: Reminder) {
        // Set unique id for each
        val uniqueId = UUID.randomUUID().toString()
        reminder.id = uniqueId

        val list = getListReminder()
        list.add(reminder).let {
            if (it) {
                with(sharePref.edit()) {
                    putString(ITEM_LIST, listToJsonString(list))
                    apply()
                }
            }
        }
    }

    fun updateReminderStatus(reminder: Reminder, status: Boolean) {
        val list = getListReminder()
        val location = list.indexOf(reminder)
        list[location].checked = status

        with(sharePref.edit()) {
            putString(ITEM_LIST, listToJsonString(list))
            apply()
        }
    }

    fun clearAll() {
        with(sharePref.edit()) {
            putString(ITEM_LIST, "")
            apply()
        }
    }

    private fun updateReminder(list: MutableList<Reminder>) {
        with(sharePref.edit()) {
            putString(ITEM_LIST, listToJsonString(list))
            apply()
        }
    }

    fun getListReminder(): MutableList<Reminder> {
        val registeredItem = sharePref.getString(ITEM_LIST, "") ?: ""
        return if (registeredItem.isNotEmpty()) {
            jsonStringToList(registeredItem)
        } else {
            mutableListOf()
        }
    }

    fun removeItem(itemId: String) {
        val list = getListReminder()
        val item = list.find { it.id == itemId }
        list.remove(item)

        updateReminder(list)
    }

    private fun listToJsonString(list: List<Reminder>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    private fun jsonStringToList(jsonString: String): MutableList<Reminder> {
        val gson = Gson()
        val type = object : TypeToken<List<Reminder>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}