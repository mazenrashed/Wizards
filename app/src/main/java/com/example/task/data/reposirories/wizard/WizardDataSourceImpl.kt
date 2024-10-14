package com.example.task.data.reposirories.wizard

import com.example.task.data.models.Wizard
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WizardDataSourceImpl(private val realm: Realm) : WizardDataSource {
    override fun getWizards(): Flow<List<Wizard>> {
        return realm.query(Wizard::class).asFlow().map { it.list }
    }

    override suspend fun saveWizards(wizards: List<Wizard>) {
        realm.write {
            wizards.forEach {
                copyToRealm(it)
            }

        }
    }


}