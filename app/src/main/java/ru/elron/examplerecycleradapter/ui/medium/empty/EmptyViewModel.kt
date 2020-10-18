package ru.elron.examplerecycleradapter.ui.medium.empty

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.elron.examplerecycleradapter.App
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter
import java.util.*

class EmptyViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    companion object {
        const val STATE_IDLE = 0
        const val STATE_LOADING = 1
        const val STATE_COMPLETED = 2
    }

    lateinit var nameArray: Array<String>
    val adapter = RecyclerAdapter<EmptyObservable>()

    val stateLiveData = MutableLiveData<Int>(STATE_IDLE)

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        EmptyViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    fun loadContent() {
        if (stateLiveData.value == STATE_IDLE) {
            stateLiveData.postValue(STATE_LOADING)
            viewModelScope.launch(Dispatchers.IO) {
                delay(5000)

                nameArray = getApplication<App>().resources.getStringArray(R.array.names)
                val list = adapter.observableList
                for (i in 0..nameArray.lastIndex)
                    list.add(EmptyObservable(nameArray[i]))

                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                    stateLiveData.postValue(STATE_COMPLETED)
                }
            }
        }
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) { }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]

}

class EmptyViewModelFactory(
    private val application: Application,
    private val owner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return EmptyViewModel(application, handle) as T
    }
}
