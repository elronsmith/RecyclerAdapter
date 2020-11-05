package ru.elron.examplerecycleradapter.ui.medium.diffutils

import androidx.recyclerview.widget.DiffUtil

class MyDiffutilCallback(val oldList: List<DiffutilsObservable>,
                         val newList: List<DiffutilsObservable>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition])
    }
}
