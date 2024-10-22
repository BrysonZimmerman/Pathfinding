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
    val tiles = WorldData(ArrayList<ArrayList<TileProperties>>(20))

    //Returns a coordinate pair
    fun getRandomLocation(): Tile {
        return Tile(Pair((0..tiles.data.size).random(), (0..tiles.data[0].size).random()))
    }

    fun setSize(x: Int, y: Int) {
        tiles.setSize(x, y)
    }

    //TODO: toString method
    //Reads array left to right, top to bottom
    //Only looks at SOUTH and EAST connections
    //Either connection exists or it does not, whitespace character for exists, some block-appearing char for not
    //Needs one monowidth char space between each column of array
    //Needs one line between each row, line containing vertical connections
}