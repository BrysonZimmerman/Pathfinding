import kotlin.test.Test
import kotlin.test.BeforeTest
import technology.zim.MazeFinder
import technology.zim.World
import technology.zim.data.Tile


class MazeFinderTest {

    @BeforeTest
    fun setup() {
        World.setSize(10, 10)
        MazeFinder.primsAlgorithm()
    }

    //Top row must have at least one south connection and no north connections
    @Test
    fun topRowOutOfBoundsCheck() {
        var southExists = false
        for(x in 0..<World.sizeX) {
            assert(!World.get(Tile(x, 0)).isNorth())
            if(World.get(Tile(x, 0)).isSouth())
                southExists = true

        }
        assert(southExists)
    }

    //Bottom row cannot have any south connections
    @Test
    fun bottomRowOutOfBoundsCheck() {
        for(x in 0..<World.sizeX) {
            assert(!World.get(Tile(x, World.sizeY-1)).isSouth())
        }
    }

    @Test
    fun allTilesVisited() {
        for(x in 0..<World.sizeX) {
            for(y in 0..<World.sizeY) {
                assert(World.get(Tile(x, y)).visited())
            }
        }
    }

    @Test
    fun allTilesHaveConnections() {
        for(x in 0..<World.sizeX) {
            for(y in 0..<World.sizeY) {
                val tileprop = World.get(Tile(x, y))
                if(!tileprop.visited())
                    println("Empty Connections at: " + Tile(x, y).toString())
                assert(tileprop.visited())
            }
        }
    }
}