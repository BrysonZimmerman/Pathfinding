package technology.zim

import it.unimi.dsi.util.XoShiRo256PlusRandom
import technology.zim.data.Directions
import technology.zim.data.Tile
import kotlin.collections.ArrayList


//Build the maze
//Options:
//DFS - Long corridors
//Prim's - Solid maze
//Wall-builder?
//https://emmilco.github.io/path_finder/

//http://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm
//http://weblog.jamisbuck.org/2011/1/3/maze-generation-kruskal-s-algorithm

object MazeFinder {
    val frontier = ArrayList<Tile>()
    val randGen = XoShiRo256PlusRandom()

    fun <E> ArrayList<E>.swapRemove(index: Int) {
        this[index] = this[this.lastIndex]
        this.removeLast()
    }

    fun primsAlgorithm() {
        //Prime the graph with the first connection, which marks the first visited Tiles
        val startingTile = World.getRandomLocation()
        val connectorTile = startingTile.getAdjacentTiles(false).random()

        //Connect the first two tiles so they're recognized as in the graph
        startingTile.connect(connectorTile)

        //Add their unexplored adjacent tiles to the frontier
        frontier.addAll(startingTile.getAdjacentTiles(false))
        frontier.addAll((connectorTile.getAdjacentTiles(false)))

        var current: Tile
        var inGraph: Tile
        var adjacentExplored: Set<Tile>
        while(frontier.isNotEmpty()) {
            //Grab a random tile from the frontier
            val random = randGen.nextInt(frontier.size)
            current = frontier.get(random)
            frontier.swapRemove(random)

            //Find adjacent tiles that are in the graph
            adjacentExplored = current.getAdjacentTiles(true)

            //Select a random explored tile
            inGraph = adjacentExplored.elementAt(randGen.nextInt(adjacentExplored.size))

            //Connect the frontier tile to the explored tile
            current.connect(inGraph)

            //Look around the frontier tile for unexplored tiles, add them to the frontier
            manifestDestiny(current)

            //print(World.toString())
            //println("--------------------------------------------")

        }
        println("prim")
    }

    private fun manifestDestiny(current: Tile) {
        current.getAdjacentTiles(false).forEach {
                tile ->
            val tileprops = World.get(tile)
            if(!tileprops.isManifest()) {
                World.update(tile, tileprops + Directions.MANIFEST)
                frontier.add(tile)
            }
        }
    }
    //Todo: Consider growing World with null values, then use Mazefinder to instantiate as the maze is developed
    //Consequence: World becomes nullable, requiring null checks to be added in order to avoid... issues
    //It's probable that this will only be needed at large scales

}