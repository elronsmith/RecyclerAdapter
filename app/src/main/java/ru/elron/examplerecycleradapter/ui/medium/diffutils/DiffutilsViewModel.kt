package ru.elron.examplerecycleradapter.ui.medium.diffutils

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter
import java.util.*
import kotlin.collections.ArrayList

class DiffutilsViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    companion object {
        const val STATE_IDLE = 0
        const val STATE_LOADING = 1
        const val STATE_COMPLETED = 2
    }
    private val random = Random()

    val adapter = RecyclerAdapter<DiffutilsObservable>()

    val stateLiveData = MutableLiveData<Int>(STATE_IDLE)
    val onClickLiveData = MutableLiveData<String>()

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        DiffutilsViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    fun loadContent() {
        if (stateLiveData.value == STATE_IDLE) {
            stateLiveData.postValue(STATE_LOADING)
            viewModelScope.launch(Dispatchers.IO) {
                delay(3000)

                // генерируем список
                val numberArray: Array<Long> = getRandomNumbers(100)
                val list = adapter.observableList
                for (i in 0..numberArray.lastIndex)
                    list.add(DiffutilsObservable(numberArray[i]))

                // отображаем
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                    stateLiveData.postValue(STATE_COMPLETED)
                }

                delay(3000)
                // копируем и сортируем
                val list2 = ArrayList<DiffutilsObservable>(list)
                list2.sort()

                // отображаем
                withContext(Dispatchers.Main) {
                    updateList(list2)
                }
            }
        }
    }

    private fun getRandomNumbers(count: Int): Array<Long> {
        val numberArray = arrayOfNulls<Long>(count)
        for (index in 0 until numberArray.size)
            numberArray[index] = random.nextLong()
        return numberArray as Array<Long>
    }

    private fun updateList(newList: List<DiffutilsObservable>) {
        val oldList = adapter.observableList
        val result = DiffUtil.calculateDiff(MyDiffutilCallback(oldList, newList))
        adapter.observableList.clear()
        adapter.observableList.addAll(newList)
        result.dispatchUpdatesTo(adapter)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val name = (observable as DiffutilsObservable).name
        onClickLiveData.postValue(name)
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]

}

class DiffutilsViewModelFactory(
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
        return DiffutilsViewModel(application, handle) as T
    }
}
