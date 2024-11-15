package technology.zim

import technology.zim.data.Tile
import kotlin.time.measureTime

class HierarchicalPathfinding {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val n = 1000
            println("Building maze")
            val buildMazeTime = measureTime {
                buildMaze(n)
            }

            println("Pathfinding")

            val bfsPathfinderTime = measureTime {
                BFSPathfinder.generatePath(Tile(0, 0), Tile(n-1, n-1))
            }


            val arrayBackedPathfinderTime = measureTime {
                ArrayBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, (n - 1)))
            }

            val mapBackedPathfinderTime = measureTime {
                MapBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, (n - 1)))
            }


            println(World.toString())
            println(n*n)
            println("Maze build time: ${buildMazeTime.inWholeMilliseconds} ms")
            println("BFS Pathfinder time: ${bfsPathfinderTime.inWholeMilliseconds}ms")
            println("Array-Backed Pathfinder time: ${arrayBackedPathfinderTime.inWholeMilliseconds}ms")
            println("HashMap-Backed Pathfinder time: ${mapBackedPathfinderTime.inWholeMilliseconds}ms")
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