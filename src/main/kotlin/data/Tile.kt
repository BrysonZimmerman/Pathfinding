package technology.zim.data

import technology.zim.World

@JvmInline
value class Tile(val value: Pair<Int, Int>) {

    constructor(x: Int, y: Int): this(Pair(x, y))

    //Connect two tiles together.
    //Calls utility function on the connected cell
    fun connect(dir: Directions) {
        val candidateTile = this+dir

        //Ensure that the tile is within bounds
        if(candidateTile.isInBounds())
        {
            this.getProperties().add(dir)
            (this + dir).getProperties().add(dir.opposite())
        }
        else {
            //TODO: Consider just silently not connecting out-of-bounds values
            println("Attempted to connect to outside bounds: <" +
                    candidateTile.value.first + ", " + candidateTile.value.second
                    + "> From Tile: <" + this.value.first + ", " +
                    this.value.second + ">")
            return
        }
    }

    //Gets Tile objects for all connected directions
    //Used for finding a path through the maze
    fun getConnections(): MutableSet<Tile> {
        val listTiles = mutableSetOf<Tile>()
        for (dir: Directions in getProperties().connections) {
            //Use the ghost of linear algebra to identify potential neighbor tiles
            listTiles.add(this+dir)
        }
        return listTiles
    }

    fun isInBounds(): Boolean {
        return value.first > 0 &&
            value.second > 0 &&
            value.first < World.tiles.data.size &&
            value.second < World.tiles.data[value.second].size
    }

    //Get the properties of the tile at the given coordinates
    fun getProperties(): TileProperties {
        return World.tiles.data.elementAt(value.first).elementAt(value.second)
    }

    //Get tile at given direction
    operator fun plus(dir: Directions): Tile {
        return Tile(value.first + dir.value.first, value.second + dir.value.second)
    }

    //Get tile at direction opposite of given direction
    operator fun minus(dir: Directions): Tile {
        return Tile(value.first - dir.value.first, value.second - dir.value.second)
    }
}