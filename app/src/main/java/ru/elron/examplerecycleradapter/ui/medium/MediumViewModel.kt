package ru.elron.examplerecycleradapter.ui.medium

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnLongItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter
import java.util.*
import kotlin.collections.ArrayList

class MediumViewModel : AndroidViewModel, OnLongItemClickViewHolderCallback{
    lateinit var nameArray: Array<String>
    val adapter: RecyclerAdapter<MediumObservable>
    val onLongClickLiveData = MutableLiveData<Pair<Int, MediumObservable>>()
    val random = Random()

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        nameArray = application.resources.getStringArray(R.array.names)
        val list = ArrayList<MediumObservable>()

        for (i in 0..nameArray.lastIndex)
            list.add(MediumObservable(nameArray[i]))

        adapter = RecyclerAdapter(list)
        MediumOViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val name = (observable as MediumObservable).name
        v?.let { Toast.makeText(it.context, "#$position $name", Toast.LENGTH_SHORT).show() }
    }

    override fun onLongItemClick(v: View?, observable: AObservable, position: Int) {
        onLongClickLiveData.postValue(Pair(position, observable as MediumObservable))
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]

    fun addItem(position: Int) {
        val name = nameArray[random.nextInt(nameArray.size)]
        adapter.observableList.add(position, MediumObservable(name))
        adapter.notifyItemInserted(position)
        Toast.makeText(getApplication(), "Добавлено!", Toast.LENGTH_SHORT).show()
    }

    fun deleteItem(position: Int) {
        adapter.observableList.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(getApplication(), "Удалено!", Toast.LENGTH_SHORT).show()
    }
}

class MediumViewModelFactory(
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
        return MediumViewModel(application, handle) as T
    }
}
