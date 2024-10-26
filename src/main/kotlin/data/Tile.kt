package technology.zim.data

import technology.zim.World

//Tile is a ULong that represents the X,Y coordinates of a Tile
//Contains functions necessary for accessing and manipulating Tiles

@JvmInline
value class Tile(private val value: ULong) {

    constructor(x: Int, y: Int): this(pack(x, y)) {

    }

    fun connect(candidateTile: Tile) {
        val dir = toward(candidateTile)
        connect(dir)
    }

    //Connect two tiles together.
    //Calls utility function on the connected cell
    fun connect(dir: Directions) {
        val candidateTile = this+dir

        //Ensure that the tile is within bounds
        if(candidateTile.isInBounds() && this.isInBounds())
        {
            World.update(this, getProperties().add(dir))
            World.update(candidateTile, candidateTile.getProperties().add(candidateTile.toward(this)))
        }
        else {
            //Should also never get to the point where the attempt is made
            println("Attempted to connect to outside bounds: <" +
                    candidateTile.x() + ", " + candidateTile.y()
                    + "> From Tile: <" + x() + ", " +
                    y() + ">")
            return
        }
    }

    fun toward(otherTile: Tile): Directions {
        return Directions.convertModifier(Tile(otherTile.x() - this.x(), otherTile.y() - this.y()).value)
    }

    fun getAdjacentTiles(explored:Boolean): Set<Tile> {
        val adj = mutableSetOf<Tile>()
        val dirs = Directions.ALL

        dirs.forEach { dir ->
            val candidateTile = this + dir
            //Ensure that the tile is within bounds
            if(candidateTile.isInBounds() && World.get(candidateTile).visited() == explored)
            {
                //println("$this+$dir --> $candidateTile")
                adj.add(candidateTile)
            }
        }
        if(adj.isEmpty() && explored)
            println("no explored found")
        return adj
    }

    //Arguments could be made for either World or Tile knowing whether a Tile is in bounds
    fun isInBounds(): Boolean {
        return x() >= 0 &&
            y() >= 0 &&
            x() < World.sizeX  &&
            y() < World.sizeY
    }

    //Get the properties of the tile at the given coordinates
    fun getProperties(): TileProperties {
        return World.get(this)
    }

    //Get tile at given direction
    operator fun plus(dir: Directions): Tile {
        return this + Directions.getModifier(dir)
    }

    operator fun plus(mod: ULong): Tile {
        return Tile(this.x() + x(mod), this.y() + y(mod))
    }


    //Debug function to print the coordinates of this Tile
    @SuppressWarnings
    fun getCoordinates(): Pair<Int, Int> {
        return Pair(x(), y())
    }

    fun x(): Int {
        return x(value)
    }

    //Gets the x value of the Long as though it were in coordinate form
    fun x(coords: ULong): Int {
        return (coords shr 32).toLong().toInt()
    }

    //Bitwise and to ensure the left-hand half of the Long is zero'd, then convert toInt()

    fun y():Int {
        return y(value)
    }

    //Get the y value from a Long as though it were a Tile
    fun y(coords: ULong): Int {
        return coords.toLong().toInt()
    }

    override fun toString():String {
        return "<" + x() + ", " + y() + ">"
    }

    companion object {
        fun pack(mostSignificantBits: Int, leastSignificantBits: Int): ULong {
            return (mostSignificantBits.toUInt().toULong() shl 32) or leastSignificantBits.toUInt().toULong()
        }
    }

}