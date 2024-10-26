package technology.zim

import technology.zim.data.Tile
import kotlin.math.abs

//A* to be upgraded with hierarchies

//Needs https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html
//and https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html

object PathFinder {
    val path = ArrayDeque<Tile>()

    //work along the path, marking tiles with VISITED along the way
    //if marking with visited is too expensive, just make the path and finalize it
    fun generatePath(start: Tile, end: Tile) {
        if(!start.isInBounds() || !end.isInBounds()) {
            throw IndexOutOfBoundsException("Cannot generate a path to or from an out of bounds tile")
        }







    }

    //Heuristic value, to estimate the cost of a given tile
    fun hValue(prospect: Tile, end: Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }


    //Step through the path, marking each Tile with INPATH
    fun finalizePath() {

    }

}