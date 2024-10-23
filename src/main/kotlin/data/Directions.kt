package technology.zim.data

enum class Directions(val value: Pair<Int, Int>) {
    NORTH(Pair(0, -1)) {
        override fun opposite(): Directions {
            return SOUTH
        }
    },
    SOUTH(Pair(0, 1)) {
        override fun opposite(): Directions {
            return NORTH
        }
    },
    EAST(Pair(1, 0)) {
        override fun opposite(): Directions {
            return WEST
        }
    },
    WEST(Pair(-1, 0)) {
        override fun opposite(): Directions {
            return EAST
        }
    };

    //Return the opposite direction
    abstract fun opposite(): Directions

    fun x():Int {
        return value.first
    }

    fun y():Int {
        return value.second
    }

    companion object {
        val ALL = setOf(NORTH, EAST, WEST, SOUTH)
    }

}