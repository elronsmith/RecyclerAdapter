package ru.elron.examplerecycleradapter.ui.hard.pagination

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.elron.examplerecycleradapter.view.AObservable
import ru.elron.examplerecycleradapter.view.OnItemClickViewHolderCallback
import ru.elron.examplerecycleradapter.view.RecyclerAdapter

class PaginationViewModel : AndroidViewModel, OnItemClickViewHolderCallback, FooterPaginationObservable.OnShowListener {
    companion object {
        const val STATE_IDLE = 0
        const val STATE_LOADING = 1
        const val STATE_LOADING_NEXT_PAGE = 2
        const val STATE_COMPLETED = 3
        const val STATE_END = 4
    }

    val adapter = RecyclerAdapter<AObservable>()
    private var fakeNewsRepository = FakeNewsRepository()
    private val footerPaginationObservable = FooterPaginationObservable(this)

    val stateLiveData = MutableLiveData<Int>(STATE_IDLE)
    private var lastPage = -1

    constructor(application: Application, state: SavedStateHandle) : super(application) {
        PaginationViewHolder.addViewHolder(adapter.holderBuilderArray, this)
        FooterPaginationViewHolder.addViewHolder(adapter.holderBuilderArray, this)
    }

    override fun onItemClick(v: View?, observable: AObservable, position: Int) {
        val o = (observable as PaginationObservable)
        v?.let { Toast.makeText(it.context, "#$position ${o.title}", Toast.LENGTH_SHORT).show() }
    }

    override fun getObservable(position: Int): AObservable = adapter.observableList[position]

    fun loadContent() {
        loadPage(0)
    }

    fun loadPage(page: Int) {
        when(stateLiveData.value) {
            STATE_LOADING, STATE_LOADING_NEXT_PAGE, STATE_END -> return
        }
        if (page == lastPage) return

        if (page == 0)
            stateLiveData.postValue(STATE_LOADING)
        else
            stateLiveData.postValue(STATE_LOADING_NEXT_PAGE)

        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)

            // загружаем первую страницу
            val pageNews: PageNews = fakeNewsRepository.requestGetNews(page)

            // передаём список
            withContext(Dispatchers.Main) {
                addPage(pageNews)
            }
        }
    }

    private fun addPage(pageNews: PageNews) {
        lastPage = pageNews.page
        if (pageNews.list.size == 0) {
            // пустая страница
            stateLiveData.postValue(STATE_END)
            removeFooter()
        } else {
            if (pageNews.page < 1) {
                // первая страница
                addPage(pageNews.list)
                adapter.notifyDataSetChanged()
                stateLiveData.postValue(STATE_COMPLETED)
            } else {
                // следующая страница
                val start = adapter.observableList.size-1
                addPage(pageNews.list)
                adapter.notifyItemRangeInserted(start, pageNews.list.size)
                stateLiveData.postValue(STATE_COMPLETED)
            }
        }
    }

    private fun addPage(list: java.util.ArrayList<News>) {
        removeFooter()

        for (item in list)
            adapter.observableList.add(PaginationObservable(item.title, item.subtitle))

        addFooter()
    }

    private fun removeFooter() {
        if (adapter.observableList.size > 0 && adapter.observableList.last() == footerPaginationObservable) {
            adapter.observableList.removeAt(adapter.observableList.size-1)
            adapter.notifyItemRemoved(adapter.observableList.size)
        }
    }

    private fun addFooter() {
        if (adapter.observableList.last() != footerPaginationObservable) {
            adapter.observableList.add(footerPaginationObservable)
            adapter.notifyItemInserted(adapter.observableList.size-1)
        }
    }

    override fun onShowItemFooter() {
        loadPage(lastPage+1)
    }
}

class PaginationViewModelFactory(
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
        return PaginationViewModel(application, handle) as T
    }
}

/**
 * Генератор случайных сообщений
 */
class FakeNewsRepository(private val news_count: Int = 35,
                         private val news_in_page: Int = 10) {
    fun requestGetNews(page: Int): PageNews {
        val result = PageNews()
        result.page = page

        val start = page * news_in_page
        if (start > news_count) return result

        var end = start + news_in_page
        if (end > news_count)
            end = news_count

        for (index in start until end) {
            result.list.add(News("title ${index+1}", "subtitle ${index+1}"))
        }

        return result
    }
}

class PageNews {
    var page = 0
    val list = ArrayList<News>()
}

data class News(val title: String, val subtitle: String)
