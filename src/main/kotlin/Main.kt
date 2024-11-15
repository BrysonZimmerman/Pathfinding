package technology.zim

import technology.zim.data.Directions
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

            val BFSPathFinderTime = measureTime {
                BFSPathFinder.generatePath(Tile(0, 0), Tile(n-1, n-1))
            }


            val ArrayBackedPathfinderTime = measureTime {
                ArrayBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, (n - 1)))
            }
/*
            val MapBackedPathfinderTime = measureTime {
                MapBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, (n - 1)))
            }
            */

            println(World.toString())
            println(n*n)
            println("Maze build time: ${buildMazeTime.inWholeMilliseconds} ms")
            //println("HashMap-Backed Pathfinder time: ${MapBackedPathfinderTime.inWholeMilliseconds}ms")
            println("Array-Backed Pathfinder time: ${ArrayBackedPathfinderTime.inWholeMilliseconds}ms")
            println("BFS Pathfinder time: ${BFSPathFinderTime.inWholeMilliseconds}ms")
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