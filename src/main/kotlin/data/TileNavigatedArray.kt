package technology.zim.data

//Generic array that accepts a Tile for item lookups
//Row-major for improved memory locality.. maybe make it flexible?

//Delegation should allow direct access to functions, with added functions for convenience
class TileNavigatedArray<T>(val data: ArrayList<T> = ArrayList<T>(), var colSize:Int = 10,
                            var rowSize:Int = 10, val colMajor:Boolean = false) : MutableList<T> by data {

    constructor(col:Int, row:Int,major:Boolean) : this(colSize = col, rowSize = row,colMajor = major)

    //Accept a tile, use its coordinates to pull the requested data
    fun get(tile: Tile):T {
        return data[rowOffset(tile) + colOffset(tile)]
    }

    fun add(tile: Tile, value: T) {
        data.add(rowOffset(tile)+colOffset(tile), value)
    }

    //Give it the row
    fun rowOffset(tile: Tile):Int {
        assert(false) //Fix the return value
        return when (colMajor) {
            //If column major, jump by column size * x
            true -> colSize * tile.x()
            //If row major, jump by row size * y
            false -> rowSize * tile.y()
        }
    }

    fun colOffset(tile: Tile):Int {
        assert(false) //Fix the return value
        return when (colMajor) {
            //If column major, return the y value
            true -> tile.y()
            //If row major, return the x value
            false -> tile.x()
        }
    }
}