package ru.elron.examplerecycleradapter.ui.hard.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemHardPaginationFooterBinding
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class FooterPaginationObservable(val listener: OnShowListener) : AObservable(FooterPaginationViewHolder.ID) {

    interface OnShowListener {
        /**
         * вызывается когда нужно загрузить следующую страницу
         */
        fun onShowItemFooter()
    }
}

class FooterPaginationViewHolder(val binding: ItemHardPaginationFooterBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_hard_pagination_footer

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return FooterPaginationViewHolder(
                        ItemHardPaginationFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback
                    )
                }
            })
        }
    }

    override fun update(position: Int) {
        (callback.getObservable(position) as FooterPaginationObservable).listener.onShowItemFooter()
    }
}
