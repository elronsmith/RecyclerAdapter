package ru.elron.examplerecycleradapter.ui.nightmare

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter

class NightmareViewModel : AndroidViewModel, OnItemClickViewHolderCallback {
    val adapter = RecyclerAdapter<NightmareObservable>()

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        NewViewHolder.addViewHolder(adapter.holderBuilderArray, this)
        FriendsViewHolder.addViewHolder(adapter.holderBuilderArray, this)
        ItemViewHolder.addViewHolder(adapter.holderBuilderArray, this)
        AdViewHolder.addViewHolder(adapter.holderBuilderArray, this)

        adapter.observableList.add(NightmareObservable.obtainNew())
        adapter.observableList.add(NightmareObservable.obtainFriends())
        adapter.observableList.add(NightmareObservable.obtainItem())
        adapter.observableList.add(NightmareObservable.obtainAd())
        adapter.observableList.add(NightmareObservable.obtainItem())
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        v?.let {
            when(it.id) {
                R.id.newPictureImageView -> {
                    Toast.makeText(it.context, "Открываем экран добавления картинки", Toast.LENGTH_SHORT).show()
                }
                R.id.friend1ImageView, R.id.friend2ImageView, R.id.friend3ImageView-> {
                    Toast.makeText(it.context, "Фильтр новостей", Toast.LENGTH_SHORT).show()
                }
                R.id.itemAvatarImageView -> {
                    Toast.makeText(it.context, "Фильтр новостей", Toast.LENGTH_SHORT).show()
                }
                R.id.itemPictureImageView -> {
                    Toast.makeText(it.context, "Фильтр новостей", Toast.LENGTH_SHORT).show()
                }
                R.id.itemHolder -> {
                    Toast.makeText(it.context, "Открыть пост", Toast.LENGTH_SHORT).show()
                }
                R.id.itemLikeImageView -> {
                    Toast.makeText(it.context, "Like", Toast.LENGTH_SHORT).show()
                }
                R.id.itemCommentImageView -> {
                    Toast.makeText(it.context, "Comment", Toast.LENGTH_SHORT).show()
                }
                R.id.itemRepostImageView -> {
                    Toast.makeText(it.context, "Repost", Toast.LENGTH_SHORT).show()
                }
                R.id.adHolder -> {
                    Toast.makeText(it.context, "Открыть рекламу", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]
}

class NightmareViewModelFactory(
    private val application: Application,
    private val owner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return NightmareViewModel(application, handle) as T
    }
}
