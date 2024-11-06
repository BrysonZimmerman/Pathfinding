import technology.zim.data.MinHeap
import kotlin.test.Test
import kotlin.test.BeforeTest

class MinHeapTest {
    companion object {
        var heap = MinHeap()
    }
    @BeforeTest
    fun setUp() {
        heap = MinHeap()
        arrayOf(10, 20, 15, 17, 9, 21).forEach { item ->
            heap.insert(item)
        }
    }

    @Test
    fun sortTest() {
        arrayOf(9, 10, 15, 17, 20, 21 ).forEach { item ->
            println(item)
            assert(heap.popMin() == item)
        }
    }
}