package technology.zim.data

import technology.zim.World

@JvmInline
value class Tile(val value: Long) {

    constructor(x: Int, y: Int): this(pack(x, y)) {
    }

    fun connect(candidateTile: Tile) {
        val dir = Directions.convertModifier(this.value - candidateTile.value)
        if(candidateTile.isInBounds()) {
            World.update(this, getProperties().add(dir))
        }
        else {
            throw(ArrayIndexOutOfBoundsException("Cannot connect to an out of bounds tile"))
        }
    }
    //Connect two tiles together.
    //Calls utility function on the connected cell
    fun connect(dir: Directions) {
        val candidateTile = this+dir

        //Ensure that the tile is within bounds
        if(candidateTile.isInBounds())
        {
            World.tiles.value[x()][y()] = getProperties().add(dir)
            World.tiles.value[candidateTile.x()][candidateTile.y()] = candidateTile.getProperties().add(dir)
        }
        else {
            //Shouldn't matter whether we skip connecting an out-of-bounds item
            //Should also never get to the point where the attempt is made
            println("Attempted to connect to outside bounds: <" +
                    candidateTile.x() + ", " + candidateTile.y()
                    + "> From Tile: <" + x() + ", " +
                    y() + ">")
            return
        }
    }

    //Duplicating code from getAdjacent shaved off 100ms
    //I'm keeping it
    fun getAdjacentTiles(explored:Boolean): Set<Tile> {
        val adj = mutableSetOf<Tile>()
        val dirs = Directions.ALL

        dirs.forEach { dir ->
            val candidateTile = this + dir
            //Ensure that the tile is within bounds
            if(candidateTile.isInBounds() && candidateTile.getProperties().visited() == explored)
            {
                adj.add(candidateTile)
            }
        }
        return adj
    }



    fun hasConnections(): Boolean {
        return getProperties().connections != 0
    }


    //Arguments could be made for either World or Tile knowing whether a Tile is in bounds
    fun isInBounds(): Boolean {
        return x() >= 0 &&
            y() >= 0 &&
            x() < World.tiles.value.size  &&
            y() < World.tiles.value.get(0).size
    }

    //Get the properties of the tile at the given coordinates
    fun getProperties(): TileProperties {
        return World.tiles.value.get(x()).get(y())
    }

    //Get tile at given direction
    operator fun plus(dir: Directions): Tile {
        return Tile(x() + x(dir), y() + y(dir))
    }

    //Get tile at direction opposite of given direction
    operator fun minus(dir: Long): Tile {
        return Tile(x() - x(dir), y() - y(dir))
    }

    fun x(): Int {
        return (value shr 32).toInt()
    }

    //Gets the x value of the given coordinate form
    fun x(coord: Long):Int {
        return (coord shr 32).toInt()
    }

    //Gets the x value of the coordinate form of the given direction
    fun x(dir: Directions):Int {
        return (Directions.getModifier(dir) shr 32).toInt()
    }

    fun y():Int {
        return value.toInt()
    }

    //Gets the y value of the given coordinate form
    fun y(coord: Long):Int {
        return coord.toInt()
    }

    //Gets the y value of the coordinate form of the given direction
    fun y(dir: Directions):Int {
        return y() + Directions.getModifier(dir).toInt()
    }

    override fun toString():String {
        return "<" + x() + ", " + y() + ">"
    }

    companion object {
        fun pack(x: Int, y: Int):Long {
            return (x.toLong() shl 32) + y.toLong()
        }
    }

}