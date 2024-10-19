package technology.zim

import technology.zim.data.Directions
import technology.zim.data.TileProperties
import technology.zim.data.WorldData
import java.util.*

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Each element contains a TileProperties
//  Which currently only contains the edges of the "graph", stored as directions

class World(private val tiles: WorldData) {

    //TODO: Constructor that calls Mazefinder algorithm with given dimensions,

    constructor(xmin: Int, ymin: Int) : this(WorldData(xmin, ymin))

    //Returns a coordinate pair
    fun getRandomLocation(): Pair<Int, Int> {
        return Pair((0..tiles.data.size).random(), (0..tiles.data[0].size).random())
    }

    //Get the properties of the tile at the given coordinates
    fun tile(coords: Pair<Int, Int>): TileProperties {
        return tiles.data.elementAt(coords.first).elementAt(coords.second)
    }

    //TODO: Add function for creating connections
    //fun addConnection(at: Pair<Int, Int>, dir: Directions)

    //Sort out what cells are connected. Induces some CPU overhead compared to storing a simple list
    //Benefit is smaller memory footprint by using references to singleton enums
    fun getConnections(at: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val listTiles = ArrayList<Pair<Int, Int>>()
        for (dir: Directions in tile(at).connections) {
            //Use the ghost of linear algebra to identify potential neighbor tiles
            val candidateTile = Pair<Int, Int>(at.first + dir.dif.first, at.second + dir.dif.second)

            //Ensure that the tile is within bounds
            if(candidateTile.first > 0 &&
                candidateTile.second > 0 &&
                candidateTile.first < tiles.data.size &&
                candidateTile.second < tiles.data[candidateTile.first].size) {
                    listTiles.add(candidateTile)
            }
        }
        return listTiles.toSet()
    }
}