package technology.zim.data

enum class Directions(val dif: Pair<Int, Int>) {
    NORTH(Pair(0, 1)), SOUTH(Pair(0, -1)), EAST(Pair(1, 0)), WEST(Pair(-1, 0))
}