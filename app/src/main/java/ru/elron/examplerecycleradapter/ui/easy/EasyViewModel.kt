package ru.elron.examplerecycleradapter.ui.easy

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter

class EasyViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    lateinit var adapter: RecyclerAdapter<EasyObservable>

    constructor(application: Application) : super(application) {
        val names = application.resources.getStringArray(R.array.names)
        val list = ArrayList<EasyObservable>()

        for (i in 0..names.lastIndex)
            list.add(EasyObservable(names[i]))

        adapter = RecyclerAdapter(list)
        addViewHolder(adapter.holderBuilderArray, this)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val name = (observable as EasyObservable).name
        v?.let { Toast.makeText(it.context, "#$position $name", Toast.LENGTH_SHORT).show() }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]
}
