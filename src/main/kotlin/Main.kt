package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile

class HierarchicalPathfinding {
    companion object {
        //TODO: Run tests of World with nested ArrayList vs TileNavigatedArray
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 1000
            println("Building maze")
            var startTime = System.currentTimeMillis()
            buildMaze(n)
            var endTime = System.currentTimeMillis()
            val buildMazeTime = endTime - startTime

            println("Pathfinding")

            startTime = System.currentTimeMillis()
            ArrayBackedPathfinder.generatePath(Tile(0, 0), Tile(n-1, (n-1)))
            endTime = System.currentTimeMillis()
            val ArrayBackedPathfinderTime = endTime - startTime

            //World.scrubDirections(listOf(Directions.FRONTIER, Directions.INPATH, Directions.NOPATH))

            startTime = System.currentTimeMillis()
            //MapBackedPathfinder.generatePath(Tile(0, 0), Tile(n-1, (n-1)))
            endTime = System.currentTimeMillis()
            val MapBackedPathfinderTime = endTime - startTime

            println(World.toString())
            println(n*n)
            println("Maze build time: ${buildMazeTime} ms")
            println("HashMap-Backed Pathfinder time: ${MapBackedPathfinderTime}ms")
            println("Array-Backed Pathfinder time: ${ArrayBackedPathfinderTime} ms")
        }

        //Clear the maze of pathfinding markers before running another pathfinding algorithm
        fun clearMaze() {
            World.scrubDirections(listOf(Directions.FRONTIER, Directions.INPATH))
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