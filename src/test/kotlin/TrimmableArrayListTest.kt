import technology.zim.data.Tile
import technology.zim.util.TrimmableArrayList
import kotlin.test.BeforeTest
import kotlin.test.Test

class TrimmableArrayListTest {

    @Test
    fun emptyTest() {
        val empty = TrimmableArrayList<Tile>()
        assert(empty.isEmpty())

        empty.add(Tile(0, 0))
        empty.remove(Tile(0, 0))
        assert(empty.isEmpty())

        empty.addAll(listOf(Tile(0, 0), Tile(1, 1), Tile(2, 2)))
        assert(empty.isNotEmpty())

        empty.remove(Tile(0, 0))
        println("realSize: " + empty.size() + " Size: " + empty.data.size)
        assert(empty.size() == 2)


    }


}