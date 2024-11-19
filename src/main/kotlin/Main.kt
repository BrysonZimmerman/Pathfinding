package technology.zim

import technology.zim.data.Tile
import java.util.Locale
import kotlin.time.measureTime

class HierarchicalPathfinding {
    /*
        Next steps:
            Dump results as CSV
            Dump ANSI text to file for processing into image
            Add command line options to run particular pathfinders, render particular pathfinder's markings
            Add HPA*, should be a bit easier with existing abstractions. Hard part will be the path calculations
            Use R to render syntax highlights https://github.com/KDE/syntax-highlighting/blob/master/data/syntax/kotlin.xml
                https://pandoc.org/MANUAL.html#syntax-highlighting
                https://hamel.dev/notes/quarto/highlighting.html
     */

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
            print("Tiles in Maze: ")
            println(String.format(Locale.US, "%,d", n*n))
            println("Maze build time: ${String.format(Locale.US, "%,d",buildMazeTime.inWholeMilliseconds)} ms")
            println("BFS Pathfinder time: ${String.format(Locale.US, "%,d",bfsPathfinderTime.inWholeMilliseconds)}ms")
            println("Array-Backed A* Pathfinder time: ${String.format(Locale.US, "%,d",arrayBackedPathfinderTime.inWholeMilliseconds)}ms")
            println("HashMap-Backed A* Pathfinder time: ${String.format(Locale.US, "%,d",mapBackedPathfinderTime.inWholeMilliseconds)}ms")
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