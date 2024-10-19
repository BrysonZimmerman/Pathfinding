package technology.zim.data

//Data holder for a Tile
//Should contain a mutable set of connected directions
//Later, can hold jumps to other locations other such fancy features

//For now, a simple inline class to mitigate memory usage

@JvmInline
value class TileProperties(val connections:MutableSet<Directions> = mutableSetOf<Directions>()) {
}
