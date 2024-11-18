package technology.zim

import technology.zim.data.Tile
import java.text.NumberFormat
import java.util.Locale
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

            val numberFormat = NumberFormat.getInstance(Locale.US)
            println(World.toString())
            println(numberFormat.format(n*n))
            println("Maze build time: ${numberFormat.format(buildMazeTime.inWholeMilliseconds)} ms")
            println("BFS Pathfinder time: ${numberFormat.format(bfsPathfinderTime.inWholeMilliseconds)}ms")
            println("Array-Backed Pathfinder time: ${numberFormat.format(arrayBackedPathfinderTime.inWholeMilliseconds)}ms")
            println("HashMap-Backed Pathfinder time: ${numberFormat.format(mapBackedPathfinderTime.inWholeMilliseconds)}ms")
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