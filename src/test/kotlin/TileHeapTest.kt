import technology.zim.ArrayBackedPathfinder
import technology.zim.data.Tile
import technology.zim.data.TileHeap
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.BeforeTest
class TileHeapTest {
    companion object {
        var gVals = HashMap<Tile, Int>()
        var heap = TileHeap(Tile(20, 20), this::fValue)
        private fun fValue(prospect: Tile, end: Tile): Int {
            return hValue(prospect, end).plus(gVals.get(prospect) ?: 0)
        }

        private fun hValue(prospect: Tile, end:Tile): Int {
            return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
        }
    }
    @BeforeTest
    fun setUp() {
        heap = TileHeap(Tile(20, 20), Companion::fValue)
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