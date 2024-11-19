package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileNavigatedArray

object BFSPathfinder {
    //In this particular situation, only a single outcome is likely. Thus, BFS always finds the perfect (and only) path
    //Skipping the Heap data structure allows better utilization of on-die cache
    var gVals:TileNavigatedArray<Int> = TileNavigatedArray<Int>()

    fun generatePath(start: Tile, end: Tile) {
        if (!start.isInBounds() || !end.isInBounds()) {
            throw IndexOutOfBoundsException("Cannot generate a path to or from an out of bounds tile")
        }

        if (start == end) {
            println("Ouroboros detected")
            return
        }
        gVals = TileNavigatedArray<Int>(World.sizeX, World.sizeY, false)

        //Queue for tiles to be checked
        val frontier = ArrayDeque<Tile>()
        frontier.addLast(start)
        gVals.set(start, 0)
        World.update(start, Directions.BFSFRONTIER)

        var current:Tile
        while (frontier.isNotEmpty()) {
            //Grab the next tile and its gValue
            current = frontier.removeFirst()
            val currentG = gVals.get(current) ?: (Int.MAX_VALUE - 1).also{throw IndexOutOfBoundsException("Couldn't get gValue in BFS")}

            // add its unexplored neighbors and update their gVals
            current.getConnections().forEach {
                tile ->
                if(!World.get(tile).isBFSFrontier()) {
                    World.update(tile, Directions.BFSFRONTIER)
                    frontier.addLast(tile)
                    gVals.set(tile, currentG + 1)
                }
            }
        }
        markPath(start, end)
    }
    fun markPath(start: Tile, end:Tile) {
        //Step through the path from end until start
        var current = end
        var lowestG = Int.MAX_VALUE
        var lowestCost = end
        while(current != start) {
            World.update(current, current.getProperties() + Directions.BFSINPATH)
            current.getConnections().forEach { candidateTile ->
                val candidateG = gVals.get(candidateTile) ?: (-1).also { println("Missing gVal at ${candidateTile.toString()}")}
                if(candidateTile.isInBounds() && candidateG != -1 && candidateG < lowestG ) {
                    lowestG = candidateG
                    lowestCost = candidateTile
                }
            }
            current = lowestCost
        }
        World.update(start, start.getProperties() + Directions.BFSINPATH)
    }
}