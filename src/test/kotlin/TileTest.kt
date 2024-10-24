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
    fun connectOutOfBoundsInvalid() {
        val bottom = Tile(1, 9)
        bottom.connect(DOWN)
        assertFalse(bottom.getProperties().isSouth())
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
    fun connectNorthSouthTest() {
        //Assumes the world is at least 10, 10 in size as set up by this test class
        val someTile = Tile(5, 5)
        someTile.connect(UP)
        val northTile = someTile + UP

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
        val northTile = someTile + UP
        val southTile = someTile + DOWN
        val westTile = someTile + LEFT
        val eastTile = someTile + RIGHT

        //Generating raw tiles without built-in bounds checking, do it here
        if(northTile.isInBounds())
            northTile.getProperties().visited()
        if(southTile.isInBounds())
            southTile.getProperties().visited()


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