package ru.elron.examplerecycleradapter.ui.easy.horizontal

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter

class HorizontalViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter: RecyclerAdapter<HorizontalObservable>

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        val list = ArrayList<HorizontalObservable>()

        for (i in 1..100)
            list.add(HorizontalObservable("Long application name $i"))

        adapter = RecyclerAdapter(list)
        HorizontalViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val name = (observable as HorizontalObservable).name
        v?.let { Toast.makeText(it.context, "#$position $name", Toast.LENGTH_SHORT).show() }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]
}

class HorizontalViewModelFactory(
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
        return HorizontalViewModel(application, handle) as T
    }
}
