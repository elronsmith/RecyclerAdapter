package ru.elron.examplerecycleradapter.ui.medium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemMediumBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.LongClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnLongItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class MediumObservable(public var name: String) : AObservable(ViewHolder.ID)

fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnLongItemClickViewHolderCallback) {
    builderList.put(ViewHolder.ID, object: ViewHolderBuilder{
        override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
            return ViewHolder(
                ItemMediumBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    })
}

class ViewHolder(val binding: ItemMediumBinding, callback: OnLongItemClickViewHolderCallback) : LongClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_medium
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as MediumObservable
        binding.textView.text = o.name
        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)
    }

}
