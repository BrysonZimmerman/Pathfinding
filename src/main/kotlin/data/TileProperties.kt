package technology.zim.data

//Data holder for a Tile
//Should contain a mutable set of connected directions
//Later, can hold jumps to other locations other such fancy features

//For now, a simple inline class to mitigate memory usage



/*@JvmInline
value */
class TileProperties(val connections:MutableSet<Directions> = mutableSetOf<Directions>()) {
    var visited = false
    //Remove a direction from the list of connections
    fun remove(dir: Directions) {
        connections.remove(dir)
    }

    //Add a direction to the list of connections
    //Should only be accessed by the Tile class
    fun add(dir: Directions) {
        connections.add(dir)
    }

    fun isWest(): Boolean {
        return connections.contains(Directions.WEST)
    }

    fun isEast():Boolean {
        return connections.contains(Directions.EAST)
    }

    fun isNorth(): Boolean {
        return connections.contains(Directions.NORTH)
    }

    fun isSouth():Boolean {
        return connections.contains(Directions.SOUTH)
    }

}
