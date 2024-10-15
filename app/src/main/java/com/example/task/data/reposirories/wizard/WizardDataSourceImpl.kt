package com.example.task.data.reposirories.wizard

import com.example.task.data.models.Wizard
import com.example.task.data.remote.ApiEndPoints
import com.example.task.data.remote.DefaultResponse
import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WizardDataSourceImpl @Inject constructor(
    private val realm: Realm,
    private val apiEndPoints: ApiEndPoints
) :
    WizardDataSource {
    override suspend fun getWizards(): Flow<List<Wizard>> {
        return realm.query(Wizard::class).asFlow().map { it.list }
    }

    override suspend fun saveWizards(wizards: List<Wizard>) {
        realm.write {
            wizards.forEach {
                copyToRealm(it)
            }
        }
    }

    override suspend fun fetchWizards(): DefaultResponse<List<Wizard>> {
       return withContext(Dispatchers.IO){
           try {
               val result = apiEndPoints.getWizards()
               result.body()?.let { saveWizards(it) }
               DefaultResponse.create(apiEndPoints.getWizards())
           } catch (e: Exception) {
               DefaultResponse.create(e)
           }
       }
    }


}