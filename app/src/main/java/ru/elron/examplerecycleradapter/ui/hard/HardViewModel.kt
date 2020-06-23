package ru.elron.examplerecycleradapter.ui.hard

import android.app.Application
import android.os.Bundle
import android.os.Handler
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
import java.util.*

class HardViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter = RecyclerAdapter<HardObservable>()

    lateinit var fakeMessages: FakeMessages

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        MessageViewHolder.addViewHolder(adapter.holderBuilderArray, this)
        SystemMessageViewHolder.addViewHolder(adapter.holderBuilderArray, this)

        val nameArray = application.resources.getStringArray(R.array.names)
        val messageArray = application.resources.getStringArray(R.array.messages)
        fakeMessages = FakeMessages(adapter, nameArray, messageArray)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val message = (observable as HardObservable).message
        v?.let { Toast.makeText(it.context, "#$position $message", Toast.LENGTH_SHORT).show() }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]
}

class HardViewModelFactory(
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
        return HardViewModel(application, handle) as T
    }
}

/**
 * Генератор случайных сообщений
 */
class FakeMessages(
    val adapter: RecyclerAdapter<HardObservable>,
    val nameArray: Array<String>,
    val messageArray: Array<String>
) {
    val random = Random()
    val handler = Handler()
    var isEnabled = false

    val runnable = object : Runnable {
        override fun run() {
            adapter.observableList.add(0, obtainObserver())
            adapter.notifyItemInserted(0)

            handler.postDelayed(this, 1000)
        }
    }

    private fun obtainObserver(): HardObservable = when(random.nextBoolean()) {
        true -> {
            val name = nameArray[random.nextInt(nameArray.size)]
            val message = messageArray[random.nextInt(messageArray.size)]
            HardObservable.obtainMessage("$name: $message")
        }
        false -> {
            HardObservable.obtainSystemMessage("System message")
        }
    }

    fun startMessaging() {
        if (isEnabled) return

        handler.postDelayed(runnable, 1000)
    }
}
