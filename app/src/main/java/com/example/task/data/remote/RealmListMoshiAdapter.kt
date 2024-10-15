package com.example.task.data.remote

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class RealmListAdapter<T : RealmObject?>(private val elementAdapter: JsonAdapter<T>) :
    JsonAdapter<RealmList<T?>>() {


    override fun toJson(writer: JsonWriter, value: RealmList<T?>?) {
        writer.beginArray()
        for (element in value!!) {
            elementAdapter.toJson(writer, element)
        }
        writer.endArray()

    }

    override fun fromJson(reader: JsonReader): RealmList<T?> {
        val result: RealmList<T?> = realmListOf()
        reader.beginArray()
        while (reader.hasNext()) {
            result.add(elementAdapter.fromJson(reader))
        }
        reader.endArray()
        return result

    }
}