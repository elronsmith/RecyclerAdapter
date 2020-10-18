package ru.elron.examplerecycleradapter.ui.ultranightmare.swype

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.savedstate.SavedStateRegistryOwner
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.*

class SwypeViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter = RecyclerAdapter<SwypeObservable>()

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        SwypeViewHolder.addViewHolder(adapter.holderBuilderArray, this)

        val names = application.resources.getStringArray(R.array.names)
        for (i in 0..names.lastIndex)
            adapter.observableList.add(SwypeObservable(names[i]))
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {

    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]

    fun setupDragAndDrop(recyclerView: RecyclerView) {
        SwypeViewHolder.ITEM_TOUCH_HELPER = ItemTouchHelper(SwypeCallback(adapter))
        SwypeViewHolder.ITEM_TOUCH_HELPER!!.attachToRecyclerView(recyclerView)
    }

    override fun onCleared() {
        SwypeViewHolder.ITEM_TOUCH_HELPER = null
        super.onCleared()
    }
}

class SwypeViewModelFactory(
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
        return SwypeViewModel(application, handle) as T
    }
}
