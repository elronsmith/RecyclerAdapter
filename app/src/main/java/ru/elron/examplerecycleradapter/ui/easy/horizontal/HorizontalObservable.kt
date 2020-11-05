package ru.elron.examplerecycleradapter.ui.easy.horizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemEasyHorizontalBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class HorizontalObservable(public var name: String) : AObservable(HorizontalViewHolder.ID)

class HorizontalViewHolder(val binding: ItemEasyHorizontalBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_easy

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return HorizontalViewHolder(ItemEasyHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback)
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as HorizontalObservable
        binding.textView.text = o.name
        binding.rootItem.setOnClickListener(this)
    }
}
