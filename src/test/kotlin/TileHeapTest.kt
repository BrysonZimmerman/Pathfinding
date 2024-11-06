import technology.zim.data.TileHeap
import kotlin.test.Test
import kotlin.test.BeforeTest

//TODO: update to use Tile adjustment to heap

class TileHeapTest {
    companion object {
        var heap = TileHeap()
    }
    @BeforeTest
    fun setUp() {
        heap = TileHeap()
        arrayOf(10, 20, 15, 17, 9, 21).forEach { item ->
            heap.insert(item)
        }
    }

    @Test
    fun sortTest() {
        arrayOf(9, 10, 15, 17, 20, 21 ).forEach { item ->
            val popped = heap.popMin()
            println("$item: Got $popped")

            assert(popped == item)
        }
    }
}