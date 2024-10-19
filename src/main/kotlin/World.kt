package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileProperties
import technology.zim.data.WorldData
import java.util.*

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Each element contains a TileProperties
//  Which currently only contains the edges of the "graph", stored as directions

object World {
    val tiles = WorldData(ArrayList<ArrayList<TileProperties>>())

    //Returns a coordinate pair
    fun getRandomLocation(): Tile {
        return Tile(Pair((0..tiles.data.size).random(), (0..tiles.data[0].size).random()))
    }

    //Get the properties of the tile at the given coordinates
    fun getProperties(tile: Tile): TileProperties {
        return tiles.data.elementAt(tile.loc.first).elementAt(tile.loc.second)
    }


    //fun addConnection(at: Pair<Int, Int>, dir: Directions)

    //Sort out what cells are connected. Induces some CPU overhead compared to storing a simple list
    //Benefit is smaller memory footprint by using references to singleton enums
    fun getConnections(at: Tile): Set<Tile> {
        val listTiles = ArrayList<Tile>()
        for (dir: Directions in getProperties(at).connections) {
            //Use the ghost of linear algebra to identify potential neighbor tiles
            val candidateTile = Tile(Pair(at.loc.first + dir.dif.first, at.loc.second + dir.dif.second))

            //Ensure that the tile is within bounds
            if(candidateTile.loc.first > 0 &&
                candidateTile.loc.second > 0 &&
                candidateTile.loc.first < tiles.data.size &&
                candidateTile.loc.second < tiles.data[candidateTile.loc.first].size) {
                    listTiles.add(candidateTile)
            }
        }
        return listTiles.toSet()
    }
}