package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileProperties
import technology.zim.data.WorldData
import java.util.*
import technology.zim.data.Directions.*
import technology.zim.data.TileNavigatedArray

//Singleton object containing a set of tiles
//Has helper functions included

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Each element contains a TileProperties
//  Which currently only contains the edges of the "graph", stored as directions

object World {
    //Default size should be 10
    val tiles = TileNavigatedArray<TileProperties>()
    var sizeX = 10 //Default size
    var sizeY = 10
    const val ANSI_RESET = "\u001B[0m"
    const val ANSI_BLACK = "\u001B[30m"
    const val ANSI_RED = "\u001B[31m"
    const val ANSI_GREEN = "\u001B[32m"
    const val ANSI_YELLOW = "\u001B[33m"
    const val ANSI_BLUE = "\u001B[34m"
    const val ANSI_PURPLE = "\u001B[35m"
    const val ANSI_CYAN = "\u001B[36m"
    const val ANSI_WHITE = "\u001B[37m"


    fun update(tile: Tile, to: TileProperties) {
       tiles.set(tile, to)
    }

    fun get(tile: Tile): TileProperties {
        return tiles.get(tile)?: TileProperties(0)
    }

    //Returns a coordinate pair
    fun getRandomLocation(): Tile {
        return Tile((0..<sizeX).random(), (0..<sizeY).random())
    }

    fun setSize(x: Int, y: Int) {
        sizeX = x
        sizeY = y
        tiles.resize(x, y)
    }

    //Accepts a list of directions, removes those directions from every TileProperties in WorldData
    fun scrubDirections(rem: List<Directions>) {
        var mask = rem.fold(0) { sum, element -> sum + element.dir}
        mask = mask.inv()
        tiles.forEachIndexed {
                index, tile ->
                tiles[index] = TileProperties((tile?:TileProperties(0)).connections and(mask))
        }
    }


    override fun toString(): String {

        val str = StringBuilder()
        var inPath = false
        var checked = false
        //Reading left to right, top to bottom - can do a simple for each on the rows
        for (y in 0..sizeY - 1) {
            //Upper line: Print each tile, print right-hand connections
            for (x in 0..sizeX - 1) {
                if(get(Tile(x, y)).connections and(INPATH.dir) != 0)
                    inPath = true
                else if (get(Tile(x, y)).connections and(FRONTIER.dir) != 0)
                    checked = true
                if(inPath)
                    str.append(ANSI_GREEN)
                else if(checked)
                    str.append(ANSI_YELLOW)
                str.append(getTileShapeDoubles(get(Tile(x, y))))
                if(inPath || checked)
                    str.append(ANSI_RESET)
                inPath = false
                checked = false
            }
            str.appendLine()
        }
        str.appendLine()
        return str.toString()
    }

    fun getTileShape(tile: TileProperties): Char {
        return when(tile.connections and(UP.dir+DOWN.dir+LEFT.dir+RIGHT.dir )) {
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
        return when(tile.connections and(UP.dir+DOWN.dir+LEFT.dir+RIGHT.dir )) {
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