package technology.zim.data

@JvmInline
value class Tile(val loc: Pair<Int, Int>) {
    //Todo: Function for cell to add a connection
    //Todo: Refactor so Tiles know how to get their connections, not the world
}