package com.example.task.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realm.kotlin.types.RealmObject

@JsonClass(generateAdapter = true)
class Elixir : RealmObject {
    @Json(name = "id")
    var id: String = ""
    @Json(name = "name")
    var name: String? = null
    @Json(name = "effect")
    var effect: String? = null
}
