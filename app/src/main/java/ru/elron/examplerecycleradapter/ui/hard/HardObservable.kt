package ru.elron.examplerecycleradapter.ui.hard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemHardMessageBinding
import ru.elron.examplerecycleradapter.databinding.ItemHardMessageSystemBinding
import ru.elron.examplerecycleradapter.view.*

class HardObservable : AObservable {
    val message: String

    private constructor(mMessage: String, id: Int) : super(id) {
        this.message = mMessage
    }

    companion object {
        fun obtainMessage(message: String): HardObservable = HardObservable(
            message,
            MessageViewHolder.ID)
        fun obtainSystemMessage(message: String): HardObservable = HardObservable(
            message,
            SystemMessageViewHolder.ID)
    }
}

class MessageViewHolder(val binding: ItemHardMessageBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_hard_message

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return MessageViewHolder(
                        ItemHardMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback
                    )
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as HardObservable
        binding.textView.text = o.message
        binding.root.setOnClickListener(this)
    }
}

class SystemMessageViewHolder(val binding: ItemHardMessageSystemBinding, callback: ViewHolderCallback) : ViewHolder<ViewHolderCallback>(binding.root, callback) {
    companion object {
        val ID = R.layout.item_hard_message_system

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: ViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return SystemMessageViewHolder(
                        ItemHardMessageSystemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback
                    )
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as HardObservable
        binding.textView.text = o.message
    }
}
