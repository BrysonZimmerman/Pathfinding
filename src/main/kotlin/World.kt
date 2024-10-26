package technology.zim

import technology.zim.data.Tile
import technology.zim.data.TileProperties
import technology.zim.data.WorldData
import java.util.*

//Singleton object containing a set of tiles
//Has helper functions included

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Each element contains a TileProperties
//  Which currently only contains the edges of the "graph", stored as directions

object World {
    //Default size should be 20
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
        for(y in 0..tiles.value[0].size- 1) {
            //Upper line: Print each tile, print right-hand connections
            for (x in 0..tiles.value[0].size - 1) {
                str.append(fi)
                if(tiles.value.get(x).get(y).isEast())
                    str.append(fi)
                else
                    str.append(em)
            }
            //End upper line
            str.appendLine()

            //Lower line: Print downward connections
            for (x in 0..tiles.value.size - 1) {
                if(tiles.value.get(x).get(y).isSouth()) {
                    str.append(fi)
                }
                else
                    str.append(em)
                str.append(em)
            }
            //End lower line
            str.appendLine()
        }
        str.appendLine()
        return str.toString()
    }

    //TODO: toString method
    //Reads array left to right, top to bottom
    //Only looks at SOUTH and EAST connections
    //Either connection exists or it does not, whitespace character for exists, some block-appearing char for not
    //Needs one monowidth char space between each column of array
    //Needs one line between each row, line containing vertical connections
}