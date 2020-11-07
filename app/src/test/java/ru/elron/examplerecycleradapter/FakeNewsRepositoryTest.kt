package ru.elron.examplerecycleradapter

import org.junit.Assert
import org.junit.Test
import ru.elron.examplerecycleradapter.ui.hard.pagination.FakeNewsRepository

class FakeNewsRepositoryTest {
    val repository = FakeNewsRepository(35, 10)

    @Test
    fun requestGetNews_isCorrect() {
        var page = repository.requestGetNews(0)
        Assert.assertEquals(0, page.page)
        Assert.assertEquals(10, page.list.size)

        page = repository.requestGetNews(1)
        Assert.assertEquals(1, page.page)
        Assert.assertEquals(10, page.list.size)

        page = repository.requestGetNews(2)
        Assert.assertEquals(2, page.page)
        Assert.assertEquals(10, page.list.size)

        page = repository.requestGetNews(3)
        Assert.assertEquals(3, page.page)
        Assert.assertEquals(5, page.list.size)

    }
}
