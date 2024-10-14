package com.example.task.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject


@JsonClass(generateAdapter = true)
class Wizard : RealmObject {
    var id: String = ""

    @Json(name = "first_name")
    var firstName: String? = null

    @Json(name = "last_name")
    var lastName: String? = null

    @Json(name = "elixirs")
    var elixirs: RealmList<Elixir> = realmListOf()
}

@JsonClass(generateAdapter = true)
class WizardElixir : RealmObject {
    @Json(name = "id")
    var id: String = ""

    @Json(name = "name")
    var name: String = ""
}