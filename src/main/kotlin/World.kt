package technology.zim

import technology.zim.data.Tile
import technology.zim.data.TileProperties
import technology.zim.data.WorldData
import java.util.*
import technology.zim.data.Directions.*

//Singleton object containing a set of tiles
//Has helper functions included

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Each element contains a TileProperties
//  Which currently only contains the edges of the "graph", stored as directions

object World {
    //Default size should be 10
    val tiles = WorldData(ArrayList<ArrayList<TileProperties>>())
    var sizeX = 10
    var sizeY = 10

    fun update(tile: Tile, to: TileProperties) {
       tiles.value[tile.x()][tile.y()] = to
    }

    fun get(tile: Tile): TileProperties {
        return tiles.value[tile.x()][tile.y()]
    }

    //Returns a coordinate pair
    fun getRandomLocation(): Tile {
        return Tile((0..<sizeX).random(), (0..<sizeY).random())
    }

    fun setSize(x: Int, y: Int) {
        sizeX = x
        sizeY = y
        tiles.setSize(x, y)
    }

    //TODO: https://en.wikipedia.org/wiki/Box-drawing_characters
    //^ make the maze look like a maze
    override fun toString(): String {
        val em = ' ' //Empty character
        val fi = '█' //Filled character
        val dot = '•'

        val str = StringBuilder()


        //Reading left to right, top to bottom - can do a simple for each on the rows
        for (y in 0..tiles.value[0].size - 1) {
            //Upper line: Print each tile, print right-hand connections
            for (x in 0..tiles.value[0].size - 1) {
                str.append(getTileShapeDoubles(World.get(Tile(x, y))))
            }
            str.appendLine()
        }
        str.appendLine()
        return str.toString()
    }
    fun getTileShape(tile: TileProperties): Char {
        return when(tile.connections) {
            UP.dir+DOWN.dir+LEFT.dir+RIGHT.dir -> '╋'
            UP.dir+DOWN.dir+LEFT.dir -> '┫'
            UP.dir+DOWN.dir+RIGHT.dir -> '┣'
            UP.dir+LEFT.dir+RIGHT.dir -> '┻'
            DOWN.dir+LEFT.dir+RIGHT.dir -> '┳'
            UP.dir+LEFT.dir -> '┛'
            UP.dir + RIGHT.dir -> '┗'
            DOWN.dir + LEFT.dir -> '┓'
            DOWN.dir + RIGHT.dir -> '┏'
            LEFT.dir+RIGHT.dir -> '━'
            UP.dir+DOWN.dir -> '┃'
            UP.dir -> '╹'
            RIGHT.dir -> '╺'
            DOWN.dir -> '╻'
            LEFT.dir -> '╸'
            else -> '•'
        }
    }
    fun getTileShapeDoubles(tile: TileProperties): Char {
        return when(tile.connections and(MANIFEST.inv()) and(INPATH.inv()) and(CHECKED.inv())) {
            UP.dir+DOWN.dir+LEFT.dir+RIGHT.dir -> '╬'
            UP.dir+DOWN.dir+LEFT.dir -> '╣'
            UP.dir+DOWN.dir+RIGHT.dir -> '╠'
            UP.dir+LEFT.dir+RIGHT.dir -> '╩'
            DOWN.dir+LEFT.dir+RIGHT.dir -> '╦'
            UP.dir+LEFT.dir -> '╝'
            UP.dir + RIGHT.dir -> '╚'
            DOWN.dir + LEFT.dir -> '╗'
            DOWN.dir + RIGHT.dir -> '╔'
            LEFT.dir+RIGHT.dir -> '═'
            UP.dir+DOWN.dir -> '║'
            UP.dir -> '╨'
            RIGHT.dir -> '╞'
            DOWN.dir -> '╥'
            LEFT.dir -> '╡'
            else -> '•'
        }
    }

    //Reads array left to right, top to bottom
    //Only looks at SOUTH and EAST connections
    //Either connection exists or it does not, whitespace character for exists, some block-appearing char for not
    //Needs one monowidth char space between each column of array
    //Needs one line between each row, line containing vertical connections
}