package ru.elron.examplerecycleradapter.ui.hard

import android.app.Application
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter
import java.util.*

class HardViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter = RecyclerAdapter<HardObservable>()

    lateinit var fakeMessages: FakeMessages

    constructor(application: Application) : super(application) {
        addMessageViewHolder(adapter.holderBuilderArray, this)
        addSystemMessageViewHolder(adapter.holderBuilderArray, this)

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
