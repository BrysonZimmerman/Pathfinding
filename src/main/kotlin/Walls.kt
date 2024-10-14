package technology.zim

//Should change things around so an inline class holds the data and functions for wall checks

enum class Walls(val dirs: Int) {
    NORTH(1), EAST(2), SOUTH(4), WEST(8)
}
