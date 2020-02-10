package com.example.acculynx.data.db

import androidx.room.TypeConverter
import com.example.acculynx.data.db.entities.Answer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class GsonTypeConverter {
private var gson = Gson()

@TypeConverter
fun stringToSomeObjectList(data: String?): List<Answer?>? {
    if (data == null) {
        return Collections.emptyList()
    }
    val listType: Type = object : TypeToken<List<Answer?>?>() {}.type
    return gson.fromJson<List<Answer?>>(data, listType)
}

@TypeConverter
fun someObjectListToString(someObjects: List<Answer?>?): String? {
    return gson.toJson(someObjects)
}
}