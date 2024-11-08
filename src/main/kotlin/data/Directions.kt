package technology.zim.data

enum class Directions(val dir: Int) {
    NONE(0),
    UP(1),
    DOWN(2),
    LEFT(4),
    RIGHT(8),
    MANIFEST(16),//Was added to MazeFinder's frontier, but the pathfinder has a frontier so this is named something other than frontier
    FRONTIER(32), //Added to the pathfinder's frontier
    INPATH(64), //Chosen by the pathfinder
    ;

    companion object {

        //Todo: This breaks the connect() function when used
        fun opposite(dir: Directions): Directions {
            return when(dir) {
                UP -> DOWN
                DOWN -> UP
                LEFT -> RIGHT
                RIGHT -> LEFT
                else -> NONE
            }
        }
        fun getModifier(dir: Directions): ULong {
            return when(dir) {
                UP -> NORTH
                DOWN -> SOUTH
                LEFT -> WEST
                RIGHT -> EAST
                else -> NONEMOD
            }
        }

        fun convertModifier(mod: ULong): Directions {
            return when(mod) {
                NORTH -> UP
                SOUTH -> DOWN
                WEST -> LEFT
                EAST -> RIGHT
                else -> NONE
            }
        }

        const val SOUTH: ULong = 1UL
        const val NORTH: ULong = 4294967295UL
        const val WEST: ULong = 18446744069414584320UL
        const val EAST: ULong = 4294967296UL
        val ALL = arrayOf(UP, DOWN, LEFT, RIGHT)
        const val NONEMOD = 0UL
    }

    fun inv(): Int {
        return dir.inv()
    }

}