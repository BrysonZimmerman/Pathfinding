package technology.zim

import technology.zim.data.Tile
import kotlin.times
import kotlin.toString

/*
Adjacency matrix for graph representation. 5k x 5k or 10k x 10k are safe goals, for memory
Working with a gigantic grid that's mostly full: better to have a up/right/down/left data in each one or a huge adjacency matrix?
Minimum int size is probably 32 or 64 bit, so huge memory waste if that's used


Abandon fancy stretch goals, just get pathfinding done and scale it up to demonstrate large scale
 */


//TODO: Figure out why attempted connections out of bounds are happening
/*
Building world
Start
Attempted to connect to outside bounds: <44, 50> From Tile: <44, 49>
no explored found
 */
class HierarchicalPathfinding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 50
            buildMaze(n)
            val startTime = System.currentTimeMillis()
            PathFinder.generatePath(Tile(0, 0), Tile(n-1, (n-1)))
            val endTime = System.currentTimeMillis()
            println(World.toString())
            println(n*n)
            println((endTime - startTime).toString() + "ms")
        }

        fun buildMaze(n: Int) {

            println("Building world")
            World.setSize(n, n)
            println("Start")

            try {
                MazeFinder.primsAlgorithm()
            } catch(e: Exception) {
                println(World.toString())
                println(e.message)
            }


        }
    }

}