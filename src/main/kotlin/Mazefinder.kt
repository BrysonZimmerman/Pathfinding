package technology.zim

import it.unimi.dsi.util.XoRoShiRo128PlusRandom
import technology.zim.data.Directions
import technology.zim.data.Tile
import technology.zim.data.TileProperties
import technology.zim.util.TrimmableArrayList
import kotlin.collections.ArrayList
import kotlin.collections.forEach
import kotlin.random.Random


//Build the maze
//Options:
//DFS - Long corridors
//Prim's - Solid maze
//Wall-builder?
//https://emmilco.github.io/path_finder/

//http://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm
//http://weblog.jamisbuck.org/2011/1/3/maze-generation-kruskal-s-algorithm

object MazeFinder {
    val frontier = TrimmableArrayList<Tile>()
    var startingTile = World.getRandomLocation()
    val randGen = XoRoShiRo128PlusRandom()

    val tempInGraph = ArrayList<Pair<Tile, TileProperties>>()

    fun primsAlgorithm() {
        cleanUp()
        //val randomGen = Random



        startingTile = World.getRandomLocation()
        startingTile.getProperties().visited = true
        //Choose an arbitrary vertex from G (the graph), and add it to some (initially empty) set V.
        frontier.addAll(startingTile.getAdjacentTiles(false))

        //Choose a random edge from G, that connects a vertex in V with another vertex not in V.
        var current: Tile
        var inGraph: Pair<Tile, Directions>
        var adjacentExplored: Set<Pair<Tile, Directions>>
        while(frontier.isNotEmpty()) {
            //Grab a random tile from the frontier, mark it as visited
            val random = randGen.nextInt(frontier.size())
            current = frontier.data[random]
            current.getProperties().visited = true
            frontier.removeAt(random)


            //Find adjacent tiles that are in the graph
            adjacentExplored = current.getAdjacent(true)

            //Select a random tile from possibleTiles
            inGraph = adjacentExplored.elementAt(randGen.nextInt(adjacentExplored.size))

            //Connect the frontier tile to the graph
            current.connect(inGraph.second)
            //Add current's unexplored tiles to the frontier, if not already in frontier
            frontier.addAll(current.getAdjacentTiles(false))


            //print(World.toString())
            //println("--------------------------------------------")

        }
        println("prim")
    }


    fun cleanUp() {
        //Clean up the frontier
        frontier.clear()
    }
    //Todo: Consider growing World with null values, then use Mazefinder to instantiate as the maze is developed
    //Consequence: World becomes nullable, requiring null checks to be added in order to avoid... issues
    //It's probable that this will only be needed at large scales

}