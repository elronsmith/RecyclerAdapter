package ru.elron.examplerecycleradapter.ui.ultranightmare.swype

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.ItemTouchHelper
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ItemUltraNightmareSwypeBinding
import ru.elron.examplerecycleradapter.ui.ultranightmare.longclick.LongClickViewHolder
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.ClickableViewHolder
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.ViewHolderBuilder

class SwypeObservable(public var name: String) : AObservable(SwypeViewHolder.ID) {
}

class SwypeViewHolder(val binding: ItemUltraNightmareSwypeBinding,
                      callback: OnItemClickViewHolderCallback) :
    ClickableViewHolder(binding.root, callback), View.OnTouchListener {
    companion object {
        val ID = R.layout.item_ultra_nightmare_swype

        var ITEM_TOUCH_HELPER: ItemTouchHelper? = null

        fun addViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
            builderList.put(ID, object: ViewHolderBuilder{
                override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
                    return SwypeViewHolder(
                        ItemUltraNightmareSwypeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        callback)
                }
            })
        }
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as SwypeObservable
        binding.textView.text = o.name
        binding.root.setOnClickListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN)
            LongClickViewHolder.ITEM_TOUCH_HELPER?.startDrag(this)
        return false
    }
}
