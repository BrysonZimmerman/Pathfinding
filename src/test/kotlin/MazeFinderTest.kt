import kotlin.test.Test
import technology.zim.MazeFinder
import technology.zim.World
import technology.zim.data.Tile
import kotlin.test.BeforeTest

class MazeFinderTest {

    @BeforeTest
    fun setup() {
        World.setSize(10, 10)
        MazeFinder.primsAlgorithm()
    }

    @Test
    fun topRowOutOfBoundsCheck() {
        var southExists = false
        World.tiles.value.forEach {
            col ->
            if(col[0].isSouth())
                southExists = true
        }
        assert(southExists)
    }

    @Test
    fun bottomRowOutOfBoundsCheck() {
        var southNotExists = true
        World.tiles.value.forEach {
            col ->
            if(col[9].isSouth())
                southNotExists = false
        }
        assert(southNotExists)
    }

    @Test
    fun allTilesVisited() {
        World.tiles.value.forEach {
            col ->
            col.forEach {
                tile ->
                assert(tile.visited())
            }
        }
    }

    @Test
    fun allTilesHaveConnections() {
        World.tiles.value.forEachIndexed {
            x,
            col ->
            col.forEachIndexed { y,
                tileprop ->
                if(!tileprop.visited())
                    println("Empty Connections at: " + Tile(x, y).toString())
                assert(tileprop.visited())
            }
        }
    }
}