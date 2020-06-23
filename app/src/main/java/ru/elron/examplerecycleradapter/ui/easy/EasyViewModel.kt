package ru.elron.examplerecycleradapter.ui.easy

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter

class EasyViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter: RecyclerAdapter<EasyObservable>

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        val names = application.resources.getStringArray(R.array.names)
        val list = ArrayList<EasyObservable>()

        for (i in 0..names.lastIndex)
            list.add(EasyObservable(names[i]))

        adapter = RecyclerAdapter(list)
        EasyViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val name = (observable as EasyObservable).name
        v?.let { Toast.makeText(it.context, "#$position $name", Toast.LENGTH_SHORT).show() }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]
}

class EasyViewModelFactory(
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
        return EasyViewModel(application, handle) as T
    }
}
