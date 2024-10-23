package technology.zim.data

import technology.zim.World

class Tile(val value: Pair<Int, Int>): Comparable<Tile> {

    constructor(x: Int, y: Int): this(Pair(x, y))

    //Connect two tiles together.
    //Calls utility function on the connected cell
    fun connect(dir: Directions) {
        val candidateTile = this+dir

        //Ensure that the tile is within bounds
        if(candidateTile.isInBounds())
        {
            this.getProperties().add(dir)
            candidateTile.getProperties().add(dir.opposite())
            candidateTile.getProperties().visited = true
            candidateTile.getProperties().visited = true
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
            if(candidateTile.isInBounds() && candidateTile.getProperties().visited == explored)
            {
                adj.add(candidateTile)
            }
        }
        return adj
    }

    fun getAdjacent(explored:Boolean): Set<Pair<Tile, Directions>> {
        val adj = mutableSetOf<Pair<Tile, Directions>>()
        val dirs = Directions.ALL

        dirs.forEach { dir ->
            val candidateTile = this + dir
            //Ensure that the tile is within bounds
            if(candidateTile.isInBounds() && candidateTile.getProperties().visited == explored)
            {
                adj.add(Pair(candidateTile, dir))
            }
        }
        return adj
    }


    fun hasConnections(): Boolean {
        return getProperties().connections.isNotEmpty()
    }


    //Gets Tile objects for all connected directions
    //Used for finding a path through the maze
    fun getConnections(): ArrayList<Tile> {
        val listTiles = ArrayList<Tile>()
        for (dir: Directions in getProperties().connections) {
            //Use the ghost of linear algebra to identify potential neighbor tiles
            listTiles.add(this+dir)
        }
        return listTiles
    }

    //Arguments could be made for either World or Tile knowing whether a Tile is in bounds
    fun isInBounds(): Boolean {
        return x() >= 0 &&
            y() >= 0 &&
            x() < World.tiles.value.size  &&
            y() < World.tiles.value[0].size
    }

    //Get the properties of the tile at the given coordinates
    fun getProperties(): TileProperties {
        return World.tiles.value[x()][y()]
    }

    //Get tile at given direction
    operator fun plus(dir: Directions): Tile {
        return Tile(x() + dir.x(), y() + dir.y())
    }

    //Get tile at direction opposite of given direction
    operator fun minus(dir: Directions): Tile {
        return Tile(x() - dir.x(), y() - dir.y())
    }

    fun x(): Int {
        return value.first
    }

    fun y():Int {
        return value.second
    }

    override fun toString():String {
        return "<" + value.first + ", " + value.second + ">"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tile) return false

        if (x() != other.x() || y() != other.y()) return false

        return true
    }

    override fun compareTo(other: Tile): Int {
        if(x() == other.x() && y() == other.y())
            return 0
        else if(y() > other.y())
            return 1
        else if(x() > other.x())
            return 1
        else
            return -1
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}