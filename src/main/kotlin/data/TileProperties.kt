package technology.zim.data

import technology.zim.data.Directions.*


//Data holder for a Tile
//Should contain a mutable set of connected directions
//Later, can hold jumps to other locations other such fancy features

//For now, a simple inline class to mitigate memory usage



@JvmInline
value class TileProperties(val connections: Int) {

    fun visited(): Boolean {
        return connections != 0
    }

    //Remove a direction from the list of connections
    fun remove(dir: Directions): TileProperties {
        return TileProperties(connections and(dir.inv()))
    }

    //Add a direction to the list of connections
    //Should only be accessed by the Tile class
    fun add(dir: Directions): TileProperties {
        return TileProperties(connections or(dir.dir))
    }

    fun isWest(): Boolean {
        return connections and(LEFT.dir) != 0
    }

    fun isEast():Boolean {
        return connections and(RIGHT.dir) != 0
    }

    fun isNorth(): Boolean {
        return connections and(UP.dir) != 0
    }

    fun isSouth():Boolean {
        return connections and(DOWN.dir) != 0
    }

    infix fun and(dir: Directions): TileProperties {
        return TileProperties(connections and(dir.dir))
    }

    infix fun and(dir: Int): TileProperties {
        return TileProperties(connections and(dir))
    }

    infix fun or(dir: Directions): TileProperties {
        return TileProperties(connections or(dir.dir))
    }

    infix fun or(dir: Int): TileProperties {
        return TileProperties(connections or(dir))
    }
}