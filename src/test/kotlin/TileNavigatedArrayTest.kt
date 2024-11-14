import technology.zim.data.Tile
import technology.zim.data.TileNavigatedArray
import kotlin.test.BeforeTest
import kotlin.test.Test

class TileNavigatedArrayTest {
    var arr:TileNavigatedArray<Int> = TileNavigatedArray()
    @BeforeTest
    fun setUp() {
        arr = TileNavigatedArray<Int>(10, 10, false)
        arr.addAll(0..(10*10))
    }


    @Test
    fun addAndAccessTest() {
        arr.apply {
            set(Tile(0,0), -1)
            set(Tile(0,1), -2)
            set(Tile(1, 0), -3)
            set(Tile(1, 1), -4)
            set(Tile(2, 1), -5)
            set(Tile(2, 2), -6)
        }
        assert(arr.get(Tile(0, 0)) == -1)
        assert(arr.get(Tile(0, 1)) == -2)
        assert(arr.get(Tile(1, 0)) == -3)
        assert(arr.get(Tile(1, 1)) == -4)
        assert(arr.get(Tile(2, 1)) == -5)
        assert(arr.get(Tile(2, 2)) == -6)
    }
}