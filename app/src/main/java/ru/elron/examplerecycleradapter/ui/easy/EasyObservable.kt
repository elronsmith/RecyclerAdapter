package ru.elron.examplerecycleradapter.ui.easy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemEasyBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class EasyObservable(public var name: String) : AObservable(EasyViewHolder.ID)

class EasyViewHolder(val binding: ItemEasyBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_easy

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return EasyViewHolder(ItemEasyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback)
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as EasyObservable
        binding.textView.text = o.name
        binding.root.setOnClickListener(this)
    }
}
