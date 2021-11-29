package pl.kylos.marczuk.pm_fm_test.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import pl.kylos.marczuk.pm_fm_test.repository.db.AppDatabase
import pl.kylos.marczuk.pm_fm_test.repository.db.DataEntity
import pl.kylos.marczuk.pm_fm_test.repository.net.DataNet
import pl.kylos.marczuk.pm_fm_test.repository.net.NetManager
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository(
    private val application: Application
) {
    private val db = AppDatabase.getInstance(application)
    private val netManager = NetManager()
    private val list = db.dataDao().getAll()
    private val errorLiveData = MutableLiveData<String>()
    fun syncList() {
        netManager.api.getList()
            .map { it.map { it.toDb() } }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.dataDao().clearAll()
                db.dataDao().insertAll(*it.toTypedArray())
            }, {
                Log.e("PM", "Network Problem", it)
                errorLiveData.postValue("Error: ${it.message}")
            })
    }

    fun getList(): LiveData<List<Data>> {
        return Transformations.map(list) {
            it.map { it.toData() }
        }
    }

    fun isDbEmpty() = Transformations.map(db.dataDao().getAll()) {
        it.isEmpty()
    }

    fun getErrorLiveData(): LiveData<String> = errorLiveData
}

fun DataNet.toDb() = DataEntity(
    id = orderId,
    description = description,
    imageUrl = image_url,
    modificationDate = modificationDate,
    title = title
)

fun DataEntity.toData() = Data(
    id = id,
    description = description,
    imageUrl = imageUrl,
    modificationDate = modificationDate,
    title = title
)