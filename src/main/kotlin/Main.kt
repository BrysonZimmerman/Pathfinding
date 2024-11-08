package technology.zim

import technology.zim.data.Tile

class HierarchicalPathfinding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 500
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