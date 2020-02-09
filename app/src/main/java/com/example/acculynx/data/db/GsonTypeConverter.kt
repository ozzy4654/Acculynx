package com.example.acculynx.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

public class GsonTypeConverter {
var gson = Gson()

@TypeConverter

fun stringToSomeObjectList(data: String?): List<Any?>? {
    if (data == null) {
        return Collections.emptyList()
    }
    val listType: Type = object : TypeToken<List<Any?>?>() {}.type
    return gson.fromJson<List<Any?>>(data, listType)
}

@TypeConverter
fun someObjectListToString(someObjects: List<Any?>?): String? {
    return gson.toJson(someObjects)
}
}