package ru.elron.examplerecycleradapter.ui.medium.empty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemEmptyBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class EmptyObservable(public var name: String) : AObservable(EmptyViewHolder.ID)

class EmptyViewHolder(val binding: ItemEmptyBinding,
                      callback: OnItemClickViewHolderCallback
) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_empty

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return EmptyViewHolder(
                        ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback
                    )
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as EmptyObservable
        binding.textView.text = o.name
    }
}
