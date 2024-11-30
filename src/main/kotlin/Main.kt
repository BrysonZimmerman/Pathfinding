package technology.zim

import technology.zim.data.Tile
import java.io.File
import kotlin.time.measureTime

class HierarchicalPathfinding {
    /*
        Next steps:
            Add command line options to run particular pathfinders, render particular pathfinder's markings
            Add HPA*, should be a bit easier with existing abstractions. Hard part will be the path calculations
            Use R to render syntax highlights https://github.com/KDE/syntax-highlighting/blob/master/data/syntax/kotlin.xml
                https://pandoc.org/MANUAL.html#syntax-highlighting
                https://hamel.dev/notes/quarto/highlighting.html
     */

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val benchmarking = true
            val ns = arrayListOf(50, 100, 250, 500, 750, 1000).reversed()
            val iterations = 10
            val file = File("performance.csv")
            file.writeText("n,path-length,build,bfs,astar-array,astar-hashmap\n")

            if(benchmarking) {
                for (n in ns) {
                    for (i in 0 until iterations) {
                        doTheThing(n, file)
                    }
                }
            }
            else
                doTheThing(25, file)

            //Write maze to file
            File("maze.txt").writeText(World.toString())
            /*
            val numberFormat = NumberFormat.getInstance(Locale.US)
            println(World.toString())
            println(numberFormat.format(n*n))
            println("Maze build time: ${numberFormat.format(buildMazeTime.inWholeMilliseconds)} ms")
            println("BFS Pathfinder time: ${numberFormat.format(bfsPathfinderTime.inWholeMilliseconds)}ms")
            println("Array-Backed Pathfinder time: ${numberFormat.format(arrayBackedPathfinderTime.inWholeMilliseconds)}ms")
            println("HashMap-Backed Pathfinder time: ${numberFormat.format(mapBackedPathfinderTime.inWholeMilliseconds)}ms")
            */
        }

        fun doTheThing(n:Int, file:File) {
            World.clear()

            var buildMazeTime = measureTime {
                buildMaze(n)
            }

            var bfsPathfinderTime = measureTime {
                BFSPathfinder.generatePath(Tile(0, 0), Tile(n - 1, n - 1))
            }


            var arrayBackedPathfinderTime = measureTime {
                ArrayBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, n - 1))
            }

            var mapBackedPathfinderTime = measureTime {
                MapBackedPathfinder.generatePath(Tile(0, 0), Tile(n - 1, n - 1))
            }
            file.appendText("${n},${buildMazeTime.inWholeMilliseconds},${ArrayBackedPathfinder.pathLength},${bfsPathfinderTime.inWholeMilliseconds},${arrayBackedPathfinderTime.inWholeMilliseconds},${mapBackedPathfinderTime.inWholeMilliseconds}\n")

        }

        fun buildMaze(n: Int) {

            //println("Building world")
            World.setSize(n, n)
            //println("Start")

            try {
                MazeFinder.primsAlgorithm()
            } catch(e: Exception) {
                println(World.toString())
                println(e.message)
            }


        }
    }

}