package technology.zim

import technology.zim.data.Tile
import kotlin.math.abs

//A* to be upgraded with hierarchies

//Needs https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html
//and https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html

object PathFinder {

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

        //Frontier defined by a Tile heap that makes comparisons on calculated hValues

        //Frontier tiles are known to have a current cost: g(n) (number of steps from start to this node)+ calculate own distance from the end h(n)
        //Probably best to just make an array of integers, where the coordinates store the tile's g(n)

        //Always grab the lowest value of f(n) = g(n) + h(n) from the frontier, mark it visited, mark its g(n), and add its unvisited neighbors

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