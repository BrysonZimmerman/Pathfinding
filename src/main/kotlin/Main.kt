package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile

class HierarchicalPathfinding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 1000
            buildMaze(n)
            var startTime = System.currentTimeMillis()
            PathFinder.generatePath(Tile(0, 0), Tile(n-1, (n-1)))
            var endTime = System.currentTimeMillis()
            val aStarMs = endTime - startTime



            println(World.toString())
            println(n*n)
            println((endTime - startTime).toString() + "ms")
        }

        //Clear the maze of pathfinding markers before running another pathfinding algorithm
        fun clearMaze() {
            World.tiles.scrubDirections(listOf(Directions.FRONTIER, Directions.INPATH))
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