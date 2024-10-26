package technology.zim

/*
Adjacency matrix for graph representation. 5k x 5k or 10k x 10k are safe goals, for memory
Working with a gigantic grid that's mostly full: better to have a up/right/down/left data in each one or a huge adjacency matrix?
Minimum int size is probably 32 or 64 bit, so huge memory waste if that's used


Abandon fancy stretch goals, just get pathfinding done and scale it up to demonstrate large scale
 */

class HierarchicalPathfinding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 1000
            println("Building world")
            World.setSize(n, n)
            println("Start")
            val startTime = System.currentTimeMillis()
            MazeFinder.primsAlgorithm()
            val endTime = System.currentTimeMillis()

            println(World.toString())
            println(n*n)
            println((endTime - startTime).toString() + "ms")
        }
    }

}