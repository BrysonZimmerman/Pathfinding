package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileHeap
import technology.zim.data.TileNavigatedArray
import kotlin.Int
import kotlin.math.abs

//A* pathfinder backed by an array to improve efficiency

object ArrayBackedPathfinder {
    var gVals = TileNavigatedArray<Int>(World.sizeX, World.sizeY, false)
    var pathLength = 0

    fun generatePath(start: Tile, end: Tile) {
        if(!start.isInBounds() || !end.isInBounds()) {
            throw IndexOutOfBoundsException("Cannot generate a path to or from an out of bounds tile")
        }

        if(start == end) {
            println("Ouroboros detected")
            return
        }
        
        pathLength = 0
        gVals = TileNavigatedArray<Int>(World.sizeX, World.sizeY, false)
        val frontier = TileHeap(end, this::fValue)

        //Prime the frontier
        gVals.set(start, 0)
        frontier.insert(start)

        var current: Tile
        var currentG: Int

        do {
            current = frontier.popMin()
            currentG = gVals.get(current) ?: 0.also { println("Failed to get gVal that must exist") }

            current.getConnections().forEach { candidateTile ->
                val candidateG = gVals.get(candidateTile)?:-1
                //Ensure that the tile is within bounds
                if(candidateTile.isInBounds() && candidateG == -1)
                {
                    //Otherwise, the tile has been reached and this path is not better, so carry on
                    gVals.set(candidateTile, currentG + 1)
                    frontier.insert(candidateTile)
                    World.update(candidateTile, candidateTile.getProperties() +Directions.FRONTIER)
                }
            }
        } while( current != end)

        //At this point, a path is found
        markPath(start, end)
    }

    fun markPath(start: Tile, end:Tile) {
        //Step through the path from end until start
        var current = end
        var lowestG = Int.MAX_VALUE
        var lowestCost = end
        while(current != start) {
            World.update(current, current.getProperties() + Directions.INPATH)
            current.getConnections().forEach { candidateTile ->
                val candidateG = gVals.get(candidateTile) ?: -1
                if(candidateTile.isInBounds() && candidateG != -1 && candidateG < lowestG ) {
                    lowestG = candidateG
                    lowestCost = candidateTile
                }
            }
            current = lowestCost
            pathLength++
        }
        World.update(start, start.getProperties() + Directions.INPATH)
    }

    private fun fValue(prospect: Tile, end: Tile): Int {
        return hValue(prospect, end).plus(gVals.get(prospect) ?: 0)
    }

    private fun hValue(prospect: Tile, end:Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }
}