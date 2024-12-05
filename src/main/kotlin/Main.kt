package technology.zim

import technology.zim.data.Tile
import java.io.File
import kotlin.time.measureTime

class HierarchicalPathfinding {
    /*
        Next steps:
            Add command line options to run particular pathfinders, render particular pathfinder's markings
            Add HPA*, should be a bit easier with existing abstractions. Hard part will be the path calculations
     */

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val benchmarking = true
            val ns = arrayListOf(10, 25, 50, 75, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100).reversed()
            val iterations = 25
            val file = File("performance.csv")
            file.writeText("n,path-length,prims,bfs,astar-array,astar-hashmap\n")
            var rossFile = File("performance-ross.csv")
            rossFile.writeText("n,path-length,prims,bfs,astar-array,astar-hashmap\n")
            if(benchmarking) {
                for (n in ns) {
                    for (i in 0 until iterations) {
                        doTheThing(n, file)
                        val handle = Runtime.getRuntime().exec("primmaze2.exe ${n}")
                        handle.waitFor()
                        val output = handle.inputStream.bufferedReader().readText()
                        rossFile.appendText(output)
                    }
                }
            }
            else
                doTheThing(1000, File("performance-single.txt"))

            //Write maze to file
            File("maze.txt").writeText(World.toString())
            /*
            val numberFormat = NumberFormat.getInstance(Locale.US)
            println(World.toString())
            println(numberFormat.format(n*n))
            println("Maze build time: ${numberFormat.format(buildMazeTime.inWholeMicroseconds)} ms")
            println("BFS Pathfinder time: ${numberFormat.format(bfsPathfinderTime.inWholeMicroseconds)}ms")
            println("Array-Backed Pathfinder time: ${numberFormat.format(arrayBackedPathfinderTime.inWholeMicroseconds)}ms")
            println("HashMap-Backed Pathfinder time: ${numberFormat.format(mapBackedPathfinderTime.inWholeMicroseconds)}ms")
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
            file.appendText("${n},${ArrayBackedPathfinder.pathLength},${buildMazeTime.inWholeMicroseconds},${bfsPathfinderTime.inWholeMicroseconds},${arrayBackedPathfinderTime.inWholeMicroseconds},${mapBackedPathfinderTime.inWholeMicroseconds}\n")

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