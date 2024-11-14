import technology.zim.data.Tile
import technology.zim.data.TileHeap
import kotlin.test.Test
import kotlin.test.BeforeTest

//TODO: update to use Tile adjustment to heap

class TileHeapTest {
    companion object {
        var gVals = HashMap<Tile, Int>()
        var heap = TileHeap(Tile(20, 20), gVals)
    }
    @BeforeTest
    fun setUp() {
        heap = TileHeap(Tile(20, 20), gVals)
        arrayOf(Tile(0, 0), Tile(1, 1), Tile(5, 5), Tile(4, 4), Tile(19, 19), Tile(2, 2)).forEach { item ->
            heap.insert(item)
        }
    }

    @Test
    fun sortTest() {
        arrayOf(Tile(19, 19), Tile(5, 5), Tile(4, 4), Tile(2, 2), Tile(1, 1), Tile(0, 0) ).forEach { item ->
            val popped = heap.popMin()
            println("$item: Got $popped")

            assert(popped == item)
        }
    }
}