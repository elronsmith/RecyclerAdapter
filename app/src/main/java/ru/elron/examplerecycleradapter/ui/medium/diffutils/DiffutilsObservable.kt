package ru.elron.examplerecycleradapter.ui.medium.diffutils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemDiffutilsBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class DiffutilsObservable(val id: Long) : AObservable(DiffutilsViewHolder.ID), Comparable<DiffutilsObservable> {
    var name: String = id.toString()

    override fun compareTo(other: DiffutilsObservable): Int {
        return id.compareTo(other.id)
    }
}

class DiffutilsViewHolder(val binding: ItemDiffutilsBinding,
                      callback: OnItemClickViewHolderCallback
) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_diffutils

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder {
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return DiffutilsViewHolder(
                        ItemDiffutilsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback
                    )
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as DiffutilsObservable
        binding.textView.text = o.name
        binding.root.setOnClickListener(this)
    }
}
