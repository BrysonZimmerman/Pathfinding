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

        //Data structure to define the frontier, possibly a heap

        //Frontier tiles know their current cost: g(n) (number of steps from start to this node)+ calculate own distance from the end h(n)
        //Always grab the lowest value of f(n) = g(n) + h(n) from the frontier, mark it visited, add its unvisited neighbors
        //Probably best to just make an array of integers, where the coordinates store the tile's g(n)

        //Need to be able to return a tile to the frontier if a shorter path to it is found
        //This seems like a thing that should not be possible. Manhattan heuristic

        //Data structure to hold the g values
        //Data structure to mark parents (tree? hashmap?)
        //Two choices that I see:

        //Dual arrays, one for g-val and one for parent node, take advantage of bitflags in Directions

        //or add a flag for DEADEND, use a more dynamic data structure to store tile data (map?)
        //then prune tiles from the data structure when they're marked as dead ends, backtracking until a fork is available






        //return a list of steps
    }

    //Heuristic value, to estimate the cost of a given tile
    fun hValue(prospect: Tile, end: Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }


    //Step through the path, marking each Tile with INPATH
    fun finalizePath() {

    }

}