package ru.elron.examplerecycleradapter.ui.nightmare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.*
import ru.elron.examplerecycleradapter.view.*

class NightmareObservable(id: Int) : AObservable(id) {
    companion object {
        fun obtainNew(): NightmareObservable = NightmareObservable(NewViewHolder.ID)
        fun obtainFriends(): NightmareObservable = NightmareObservable(FriendsViewHolder.ID)
        fun obtainItem(): NightmareObservable = NightmareObservable(ItemViewHolder.ID)
        fun obtainAd(): NightmareObservable = NightmareObservable(AdViewHolder.ID)
    }
}

fun addNewViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
    builderList.put(NewViewHolder.ID, object: ViewHolderBuilder{
        override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
            return NewViewHolder(
                ItemNightmareNewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    })
}

fun addFriendsViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
    builderList.put(FriendsViewHolder.ID, object: ViewHolderBuilder{
        override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
            return FriendsViewHolder(
                ItemNightmareFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    })
}

fun addItemViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
    builderList.put(ItemViewHolder.ID, object: ViewHolderBuilder{
        override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
            return ItemViewHolder(
                ItemNightmareItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    })
}

fun addAdViewHolder(builderList: SparseArrayCompat<ViewHolderBuilder>, callback: OnItemClickViewHolderCallback) {
    builderList.put(AdViewHolder.ID, object: ViewHolderBuilder{
        override fun create(parent: ViewGroup): ru.elron.examplerecycleradapter.view.ViewHolder<*> {
            return AdViewHolder(
                ItemNightmareAdBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                callback
            )
        }
    })
}

class NewViewHolder(val binding: ItemNightmareNewBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_nightmare_new
    }

    override fun update(position: Int) {
        binding.newPictureImageView.setOnClickListener(this)
    }
}

class FriendsViewHolder(val binding: ItemNightmareFriendsBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_nightmare_friends
    }

    override fun update(position: Int) {
        binding.friend1ImageView.setOnClickListener(this)
    }
}

class ItemViewHolder(val binding: ItemNightmareItemBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_nightmare_item
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as NightmareObservable
        binding.itemAvatarImageView.setOnClickListener(this)
        binding.itemPictureImageView.setOnClickListener(this)
        binding.itemLikeImageView.setOnClickListener(this)
        binding.itemCommentImageView.setOnClickListener(this)
        binding.itemRepostImageView.setOnClickListener(this)
        binding.itemHolder.setOnClickListener(this)
    }
}

class AdViewHolder(val binding: ItemNightmareAdBinding, callback: OnItemClickViewHolderCallback) : ClickableViewHolder(binding.root, callback) {
    companion object {
        val ID = R.layout.item_nightmare_ad
    }

    override fun update(position: Int) {
        val o = callback.getObservable(position) as NightmareObservable
        binding.adHolder.setOnClickListener(this)
    }
}
