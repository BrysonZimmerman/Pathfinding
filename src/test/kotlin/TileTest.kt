import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestFactory
import technology.zim.MazeFinder
import technology.zim.World
import technology.zim.data.*
import technology.zim.data.Directions.*

class TileTest {
    @BeforeTest
    fun setup() {
        World.setSize(10, 10)
        MazeFinder.cleanUp()
    }

    @Test
    fun connectBottomToSouthInvalid() {
        val bottom = Tile(1, 9)
        bottom.connect(SOUTH)
        assertFalse(bottom.getConnections().contains(bottom+SOUTH))
    }

    @Test
    fun outOfBoundsTest() {
        val tooNorth = Tile(1, -1)
        val tooWest = Tile(-1, 1)
        val tooEast = Tile(10, 1)
        val tooSouth = Tile(1, 10)

        val northIn = Tile(0, 0)
        val southIn = Tile(9, 9)

        assert(northIn.isInBounds())
        assert(southIn.isInBounds())

        assertFalse(tooNorth.isInBounds())
        assertFalse(tooWest.isInBounds())
        assertFalse(tooEast.isInBounds())
        assertFalse(tooSouth.isInBounds())
    }

    @Test
    fun allDirectionsTest() {
        val dirs = NORTH.all()
        assert(dirs.containsAll(listOf(NORTH, EAST, SOUTH, WEST)))
    }

    @Test
    fun connectNorthSouthTest() {
        //Assumes the world is at least 10, 10 in size as set up by this test class
        val someTile = Tile(5, 5)
        someTile.connect(NORTH)
        val northTile = someTile + NORTH

        assert(someTile.getProperties().isNorth())
        assertFalse(someTile.getProperties().isSouth())
        assertFalse(someTile.getProperties().isWest())
        assertFalse(someTile.getProperties().isEast())

        assert(northTile.getProperties().isSouth())
        assertFalse(northTile.getProperties().isNorth())
        assertFalse(northTile.getProperties().isWest())
        assertFalse(northTile.getProperties().isEast())
    }

    @Test
    fun adjacentTest() {
        val someTile = Tile(1, 1)
        val northTile = someTile + NORTH
        val southTile = someTile + SOUTH
        val westTile = someTile + WEST
        val eastTile = someTile + EAST

        //Generating raw tiles without built-in bounds checking, do it here
        if(northTile.isInBounds())
            northTile.getProperties().visited = true
        if(southTile.isInBounds())
            southTile.getProperties().visited = true


        val adjacent = someTile.getAdjacentTiles(false)
        assertFalse(adjacent.contains(northTile))
        assertFalse(adjacent.contains(southTile))
        assert(adjacent.contains(eastTile))
        assert(adjacent.contains(westTile))

        val explored = someTile.getAdjacentTiles(true)
        assert(explored.elementAt(0) == northTile)

        assert(explored.contains(northTile))
        assert(explored.contains(southTile))
        assertFalse(explored.contains(eastTile))
        assertFalse(explored.contains(westTile))

        adjacent.forEach {
            it ->
            println(it.toString())
        }
    }


}