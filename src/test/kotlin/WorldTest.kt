import technology.zim.World
import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileProperties
import kotlin.test.*

class WorldTest {
    @BeforeTest
    fun setUp() {
        World.setSize(10, 10)
    }

    @Test
    fun updateTileTest() {
        val t = Tile(1, 1)
        val tu = t + Directions.UP
        assert(World.get(t).connections == 0)

        World.update(t, TileProperties(Directions.UP.dir))
        World.update(tu, TileProperties(Directions.DOWN.dir))

        assert(World.get(t).connections == Directions.UP.dir)
        assert(World.get(tu).connections == Directions.DOWN.dir)


    }


}