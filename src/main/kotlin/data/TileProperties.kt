package technology.zim.data

import technology.zim.World
import technology.zim.World.tiles

//Data holder for a Tile
//Should contain a mutable set of connected directions
//Later, can hold jumps to other locations other such fancy features

//For now, a simple inline class to mitigate memory usage



@JvmInline
value class TileProperties(val connections:MutableSet<Directions> = mutableSetOf<Directions>()) {

    //Remove a direction from the list of connections
    fun remove(dir: Directions) {
        connections.remove(dir)
    }

    //Add a direction to the list of connections
    //Should only be accessed by the Tile class
    fun add(dir: Directions) {
        connections.add(dir)
    }

}
