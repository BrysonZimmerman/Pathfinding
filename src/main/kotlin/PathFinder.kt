package technology.zim

import technology.zim.data.Tile
import technology.zim.data.TileHeap
import kotlin.math.abs

//A* to be upgraded with hierarchies

//Needs https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html
//and https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html

object PathFinder {
    val hVals = HashMap<Tile, Int>()
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

        val frontier = TileHeap(end)

        //Prime the things
        hVals.put(start, hValue(start, end))
        frontier.insert(start)

        var current: Tile
        //TODO: update TileHeap to utilize full f(n) instead of just h(n)
        do {
            current = frontier.popMin()
            //TODO: Can't delegate getUnexploredTiles to Tile as this prevents adding tiles back to the frontier when a better path found
            current.getUnexploredTiles(hVals).forEach {
                value ->
                frontier.insert(value)
                hVals.put(value, hVals.get(current)?.plus(1) ?: 0.also { throw IllegalStateException("Couldn't get hValue of current tile")})
            }
        } while( current != end)

        //Need to be able to return a tile to the frontier if a shorter path to it is found

        //Data structure to hold the g values
        //Parent is chosen by the lowest g(n) value

        //return a list of steps by starting at the exit, looking at the lowest g(n) neighbor
    }

    //Heuristic value, to estimate the cost of a given tile
    fun hValue(prospect: Tile, end: Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }


    //Step through the path, marking each Tile with INPATH
    fun finalizePath() {

    }


}