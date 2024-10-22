package technology.zim

import technology.zim.data.Directions
import technology.zim.data.Tile


//Build the maze
//Options:
//DFS - Long corridors
//Prim's - Solid maze
//Wall-builder?
//https://emmilco.github.io/path_finder/

//http://weblog.jamisbuck.org/2011/1/10/maze-generation-prim-s-algorithm
//http://weblog.jamisbuck.org/2011/1/3/maze-generation-kruskal-s-algorithm

class MazeFinder {
    val visited = ArrayList<ArrayList<Boolean>>(World.tiles.data.size).apply {
        this.forEach() { element -> element.fill(false) }
    }
    val frontier = mutableSetOf<Tile>()

    fun primsAlgorithm() {
        //Choose an arbitrary vertex from G (the graph), and add it to some (initially empty) set V.
        frontier.add(World.getRandomLocation())

        //Choose a random edge from G, that connects a vertex in V with another vertex not in V.
        var current: Tile
        var prospect: Pair<Tile, Directions>
        var possibleTiles: Set<Pair<Tile, Directions>>
        while(frontier.isNotEmpty()) {
            //Grab a random tile from the frontier, mark it as visited
            current = frontier.random()
            frontier.remove(current)
            visited[current.value.first][current.value.second] = true

            //Find all adjacent tiles to that tile
            possibleTiles = getUnexploredAdjacent(current)

            //It's possible that this frontier tile has no unexplored neighbors, in which case removing it from the frontier is enough
            if(possibleTiles.isNotEmpty()) {
                //Grab a random tile from the possible set
                prospect = possibleTiles.random()

                //Connect it to the current tile
                current.connect(prospect.second)

                //Add its adjacent unexplored tiles to frontier
                frontier.addAll(getUnexploredAdjacentWithoutDirection(prospect.first))
            }
        }
    }

    fun getUnexploredAdjacentWithoutDirection(tile: Tile): Set<Tile> {
        val result = mutableSetOf<Tile>()
        getUnexploredAdjacent(tile).forEach { pair -> result.add(pair.first)}
        return result
    }

    fun getUnexploredAdjacent(tile: Tile): Set<Pair<Tile, Directions>> {
            val adj = mutableSetOf<Pair<Tile, Directions>>()
            val dirs = Directions.NORTH.all()

            dirs.forEach { dir ->
                val candidateTile = tile + dir
                //Ensure that the tile is within bounds
                if(candidateTile.isInBounds() && !visited[candidateTile.value.first][candidateTile.value.second])
                {
                    adj.add(Pair(candidateTile, dir))
                }
            }
            return adj
    }

    //Todo: Consider growing World with null values, then use Mazefinder to instantiate as the maze is developed
    //Consequence: World becomes nullable, requiring null checks to be added in order to avoid... issues
    //It's probable that this will only be needed at large scales

}