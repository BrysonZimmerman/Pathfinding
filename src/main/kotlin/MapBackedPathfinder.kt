package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileHeap
import kotlin.math.abs

//A* to be upgraded with hierarchies

//Needs https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html
//and https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html

object MapBackedPathfinder {
    val gVals = HashMap<Tile, Int>()
    //work along the path, marking tiles with VISITED along the way
    //if marking with visited is too expensive, just make the path and finalize it
    fun generatePath(start: Tile, end: Tile) {
        if(!start.isInBounds() || !end.isInBounds()) {
            throw IndexOutOfBoundsException("Cannot generate a path to or from an out of bounds tile")
        }

        if(start == end) {
            println("Ouroboros detected")
            return
        }
        gVals.clear()
        val frontier = TileHeap(end, this::fValue)

        //Prime the things
        gVals.put(start, 0)
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
                    gVals.put(candidateTile, currentG + 1)
                    frontier.insert(candidateTile)
                    World.update(candidateTile, candidateTile.getProperties() +Directions.HMFRONTIER)
                }
            }
        } while( current != end)

        //At this point, a path is found
        //println("Path found!")
        markPath(start, end)
    }

    private fun fValue(prospect: Tile, end: Tile): Int {
        return hValue(prospect, end).plus(gVals.get(prospect) ?: 0)
    }

    private fun hValue(prospect: Tile, end:Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }

    fun markPath(start: Tile, end:Tile) {
        //Step through the path from end until start
        var current = end
        var lowestG = Int.MAX_VALUE
        var lowestCost = end
        while(current != start) {
            World.update(current, current.getProperties() + Directions.HMINPATH)
            current.getConnections().forEach { candidateTile ->
                val candidateG = gVals.get(candidateTile) ?: -1
                if(candidateTile.isInBounds() && candidateG != -1 && candidateG < lowestG ) {
                    lowestG = candidateG
                    lowestCost = candidateTile
                }
            }
            current = lowestCost
        }
        World.update(start, start.getProperties() + Directions.HMINPATH)
    }

}