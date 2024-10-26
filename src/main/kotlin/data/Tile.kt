package technology.zim.data

import technology.zim.World

@JvmInline
value class Tile(val value: Long) {

    constructor(x: Int, y: Int): this(pack(x, y)) {
    }

    fun connect(candidateTile: Tile) {
        val dir = Directions.convertModifier(candidateTile.value - this.value)
        connect(dir)
    }
    //Connect two tiles together.
    //Calls utility function on the connected cell
    fun connect(dir: Directions) {
        val candidateTile = this+dir

        //Ensure that the tile is within bounds
        if(candidateTile.isInBounds() && this.isInBounds())
        {
            World.tiles.value[x()][y()] = getProperties().add(dir)
            World.tiles.value[candidateTile.x()][candidateTile.y()] = candidateTile.getProperties().add(Directions.opposite(dir))

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
        return this + Directions.getModifier(dir)
    }

    operator fun plus(mod: Long): Tile {
        return Tile(this.value + mod)
    }

    operator fun plus(tile: Tile): Tile {
        return Tile(x() + tile.x(), y() + tile.y())
    }


    fun x(): Int {
        return (value shr 32).toInt()
    }

    //Gets the x value of the given coordinate form
    fun x(coord: Tile):Int {
        return coord.x()
    }

    //Gets the x value of the coordinate form of the given direction
    fun x(dir: Directions):Int {
        return (this + dir).x()
    }

    fun y():Int {
        return value.toInt()
    }

    //Gets the y value of the given coordinate form
    fun y(coord: Tile):Int {
        return coord.y()
    }

    //Gets the y value of the coordinate form of the given direction
    fun y(dir: Directions):Int {
        return (this + dir).y()
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