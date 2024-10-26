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

    //Confirm that an empty map acts like it is empty
    @Test
    fun emptyTest() {
        val tile = Tile(3, 3)
        var adjacent = tile.getAdjacentTiles(false)
        assert(adjacent.size == 4)

        var explored = tile.getAdjacentTiles(true)
        assert(explored.isEmpty())


    }

    @Test
    fun squareTest() {
        val startTile = Tile(1, 1)
        var endTile = startTile

        endTile = endTile + LEFT
        endTile = endTile + UP
        endTile = endTile + RIGHT
        endTile = endTile + DOWN
        assert(startTile == endTile)
    }



    @Test
    fun connectOutOfBoundsInvalid() {
        println("Following out of bounds log entries are expected")
        println("-------------------------------------------------------------")
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

        println("-------------------------------------------------------------")
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

        assert(someTile.getAdjacentTiles(true).contains(northTile))


        assert(someTile.getProperties().isNorth())
        assertFalse(someTile.getProperties().isSouth())
        assertFalse(someTile.getProperties().isWest())
        assertFalse(someTile.getProperties().isEast())

        assert(northTile.getProperties().isSouth())
        assertFalse(northTile.getProperties().isNorth())
        assertFalse(northTile.getProperties().isWest())
        assertFalse(northTile.getProperties().isEast())
        assert(northTile.getAdjacentTiles(true).contains(someTile))
    }

    @Test
    fun adjacentTest() {
        val someTile = Tile(1, 1)
        val northTile = someTile + UP
        val southTile = someTile + DOWN
        val westTile = someTile + LEFT
        val eastTile = someTile + RIGHT

        someTile.connect(northTile)
        someTile.connect(DOWN)

        val world = World.tiles
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