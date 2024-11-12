package technology.zim

import technology.zim.data.Tile

object BFSPathFinder {
    //In this particular situation, only a single outcome is likely. Thus, BFS always finds the perfect (and only) path
    //Skipping the Heap data structure allows better utilization of on-die cache

    fun generatePath(start: Tile, end: Tile) {
        if (!start.isInBounds() || !end.isInBounds()) {
            throw IndexOutOfBoundsException("Cannot generate a path to or from an out of bounds tile")
        }

        if (start == end) {
            println("Ouroboros detected")
            return
        }

        //Queue for tiles to be checked
        val frontier = ArrayDeque<Tile>()
        frontier.addLast(start)

        while (frontier.isNotEmpty()) {
            //Grab the next tile
            
            // Mark it explored

            //record its distance-cost

            // add its unexplored neighbors
        }

    }

}